package com.service;

import com.dto.LoginDTO;
import com.dto.UserCacheDTO;
import com.pojo.User;

import java.util.List;

public interface UserService {

    List<UserCacheDTO> getUserCacheInfo();

    //****通过邮箱发送验证码
    void sendCodeByEmail(String pattern);

    //****通过手机号发送验证码 todo
    void sendCodeByPhone(String pattern);

    //****验证登录参数是否正确
    Integer checkLoginParam(LoginDTO loginDTO,Boolean isEmail);

    //****共享session，保存userId
    void setUserId(String sessionId, Integer userId);
}
