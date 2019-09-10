package com.service;

import com.dto.LoginDTO;
import com.dto.UserCacheDTO;
import com.dto.UserDTO;
import com.pojo.User;

import java.util.List;

public interface UserService {

    List<UserCacheDTO> getUserCacheInfo();

    //****通过邮箱发送验证码
    void sendCodeByEmail(String pattern);

    //****通过手机号发送验证码
    void sendCodeByPhone(String pattern);

    //****验证登录参数是否正确
    Integer checkLoginParam(LoginDTO loginDTO,Boolean isEmail);

    //****共享session，保存userId
    void setUserId(String sessionId, Integer userId);
    //****共享session，获取userId
    Integer getUserId(String sessionId);
    //****根据用户id的单复数，判断用户是Email还是手机号登录
    String getUserIdByEmail(int userId);//userId为复数
    String getUserIdByPhone(int userId);//userId为单数

    //****插入新用户信息，返回用户id
    Integer insertNewUser(UserDTO userDTO);

}
