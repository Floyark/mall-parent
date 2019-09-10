package com.serviceImpl;

import com.exception.MyException;
import com.service.SendService;
import com.util.ErrorMessage;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class SendServiceImpl implements SendService {

    @Autowired
    private JavaMailSender javaMailSender;

    //发件人地址
    @Value("${spring.mail.username}")
    private String from;

    //****发送邮件
    public void sendmail(String mailAddress,String subject ,String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true);
            //设置主题
            helper.setSubject(subject);
            //设置发件人
            helper.setFrom(from);
            //设置收件人
            helper.setTo(mailAddress);

            helper.setText("text/html;charset=utf-8",content);
            javaMailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            log.error("邮件发送失败【{}-{}-{}】{}",mailAddress,subject,content,e.getMessage());
            throw new MyException(ErrorMessage.EMAIL_SEND_ERROR);
        }
    }

    //****发送短信
    public void sendMessage(String phoneNumber,String subject ,String content) {
        //初始化clnt,使用单例方式
        YunpianClient clnt = new YunpianClient("eb64610f6637fd56fb936ba26fc08b00").init();
        //发送短信API
        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, phoneNumber);
        param.put(YunpianClient.TEXT, content);
        Result<SmsSingleSend> r = clnt.sms().single_send(param);
        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*
        //释放clnt
        clnt.close();
    }
}
