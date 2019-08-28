package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //根据手机号查询user
    User getUserByPhoneNumber(String phoneNumber);

    //通过邮箱查询user
    User getUserByEmail(String Email);
}
