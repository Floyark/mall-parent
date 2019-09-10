package com.mapper;

import com.dto.LoginDTO;
import com.dto.UserCacheDTO;
import com.dto.UserDTO;
import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User getUserByPhoneNumber(String phoneNumber);
    User getUserByEmail(String Email);



    //****缓存用户数据，检验注册时 不重复email和phone
    List<UserCacheDTO> getUserCacheInfo();
    //****用户使用邮箱登录 插入验证码
    Integer insertCodeByEmail(@Param(value = "email")String email,@Param(value = "code")String code);
    //****用户使用手机号登录 插入验证码
    Integer insertCodeByPhone(@Param(value = "phone")String phone,@Param(value = "code")String code);
    //****根据传来的 表名 email或者phone 使验证码失效
    void closeCodeStatus(@Param(value = "tableName") String tableName,@Param(value = "column") String column,@Param(value = "parttern")String pattern);
    //****判断登录名和验证码是否匹配 返回插入的id
    Integer checkCodeByEmail(LoginDTO loginDTO);
    //****判断登录名和验证码是否匹配
    Integer checkCodeByPhone(LoginDTO loginDTO);
    //****根据email获取用户的id
    Integer getUserIdByEmail(String inputAccount);
    //****根据Phone获取用户的id
    Integer getUserIdByPhone(String inputAccount);
    //****根据游客userId获取email
    String getGuestEmailByUserId(Integer userId);
    //****根据游客userId获取phone
    String getGuestPhoneByUserId(int userId);
    //****插入新user信息 返回userId
    void inserNewUser(UserDTO userDTO);
}
