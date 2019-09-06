package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dto.LoginDTO;
import com.dto.UserCacheDTO;
import com.exception.MyException;
import com.mapper.UserMapper;
import com.pojo.User;
import com.service.SendService;
import com.service.UserService;
import com.util.ErrorMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@org.springframework.stereotype.Service
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SendService sendService;
    @Resource
    RedisTemplate<String,Integer> redisTemplate;


    //用于将userId、userPhone、userEmail放到内存
    public List<UserCacheDTO> getUserCacheInfo() {
        return userMapper.getUserCacheInfo();
    }
    //****通过邮箱发送验证码
    public void sendCodeByEmail(String pattern) {

        //随机生成code，将code保存到表中
        String code = createAndSaveCode(pattern, "mail");
        //发送验证码
        sendService.sendmail(pattern,"验证码",code);
    }
    //****通过手机号发送验证码
    public void sendCodeByPhone(String pattern) {
        //随机生成code，将code保存到表中
        String code = createAndSaveCode(pattern, "phone");
        //发送验证码
        sendService.sendMessage(pattern,"验证码",code);
    }
    //****验证登录参数是否正确
    public Integer checkLoginParam(LoginDTO loginDTO,Boolean isEmail) {
        Integer codeExist = 0;
        if(isEmail){
            //email登录
            codeExist = userMapper.checkCodeByEmail(loginDTO);
            System.out.println("codeExist:"+codeExist);
            if(codeExist==1){
                //登录名和code值相匹配
                //判断是游客还是会员
                //查询数据库，返回0，说明是游客；返回其它数字，说明是会员
                Integer userId = userMapper.getUserIdByEmail(loginDTO.getInputAccount());
                System.out.println("userId"+userId);
                return (userId==null)?0:userId;
            }
        }else{
            //手机号登录
            codeExist = userMapper.checkCodeByPhone(loginDTO);
            if(codeExist==1){
                //登录名和code值相匹配
                //判断是游客还是会员
                //查询数据库，返回0，说明是游客；返回其它数字，说明是会员
                Integer userId = userMapper.getUserIdByPhone(loginDTO.getInputAccount());
                return (userId==null)?0:userId;
            }
        }
        //返回-1 表示 登录的账号和验证码不匹配
        return -1;
    }
    //****sessionId为key、userId为值 保存到redis
    public void setUserId(String sessionId, Integer userId) {
        redisTemplate.opsForValue().set(sessionId,userId);
    }
    //****sessionId为key，从redis中获取userId
    public Integer getUserId(String sessionId) {
        return redisTemplate.opsForValue().get(sessionId);
    }
    //****生成验证码，并且保存到user副表中     (未完成)删除过期的code用 quarz更改条目状态
    public String createAndSaveCode(String pattern,String type){

        //数据库中根据Email存储的表名
        String emailTableName = "user_sub_email";
        //数据库中的email字段名
        String emailColumn = "user_email";

        //数据库中根据Email存储的表名
        String phoneTableName = "user_sub_phone";
        //数据库中的email字段名
        String phoneColumn = "user_phone";



        //随机生成验证码
        String code = RandomStringUtils.random(6,false,true);


        //根据mail处理
        if(type.equals("mail")){
            //如果之前有申请过验证码，使其无效
            userMapper.closeCodeStatus(emailTableName,emailColumn,pattern);
            //将code插入 user_sub_mail表
            int result = userMapper.insertOrUpdateCodeByEmail(pattern,code);
            if(result!=1){
                throw new MyException(ErrorMessage.EMAIL_CODE_INSERT_ERROR);
            }
        }

        //根据phone处理
        if(type.equals("phone")){
            //如果之前有申请过验证码，使其无效
            userMapper.closeCodeStatus(phoneTableName,phoneColumn,pattern);
            //将code插入 user_sub_phone表
            Integer result = userMapper.insertOrUpdateCodeByPhone(pattern, code);
            if(result!=1){
                throw new MyException(ErrorMessage.PHONE_CODE_INSERT_ERROR);
            }
        }

        return code;

    }

}
