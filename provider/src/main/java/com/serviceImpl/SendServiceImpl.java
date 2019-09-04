package com.serviceImpl;

import com.exception.MyException;
import com.service.SendService;
import com.util.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
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

    //todo  发送短信
    public void sendMessage(String phoneNumber,String subject ,String content) {

    }
}
