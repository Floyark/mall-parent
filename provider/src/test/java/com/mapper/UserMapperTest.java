package com.mapper;

import com.ProviderTest;
import com.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserMapperTest extends ProviderTest {


    @Autowired
    UserMapper userMapper;

    @Test
    public void getUserByPhoneNumber() {

        User currentUser = userMapper.getUserByPhoneNumber("1313131313");
        System.out.println(currentUser.toString());
    }
}