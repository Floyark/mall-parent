package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //根据手机号查询user
    User getUserByPhoneNumber(String phoneNumber);

    //完善游客用户名，手机，地址，email
    int updateUserById(User user);

    //通过邮箱查询user
    User getUserByEmail(String Email);
}
