package com.mapper;

import com.ProviderTest;
import com.dto.UserCacheDTO;
import com.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UserMapperTest extends ProviderTest {


    @Autowired
    UserMapper userMapper;

    @Test
    public void getUserByPhoneNumber() {

        User currentUser = userMapper.getUserByPhoneNumber("1313131313");
        System.out.println(currentUser.toString());
    }

    @Test
    public void getUserCacheInfo() {
        List<UserCacheDTO> userCacheInfo = userMapper.getUserCacheInfo();
        for (UserCacheDTO userCacheDTO : userCacheInfo) {
            System.out.println(userCacheDTO.toString());
        }

    }

    @Test
    public void insertOrUpdateCodeByEmail() {
        Integer integer = userMapper.insertOrUpdateCodeByEmail("xxxx@163.com", "123412");
        System.out.println(integer);
    }
    @Test
    public void insertOrUpdateCodeByPhone() {
    }

    @Test
    public void closeCodeStatus(){
        String tableName = "user_sub_email";
        String column = "user_email";
        String partern = "123qwe";
        userMapper.closeCodeStatus(tableName,column,partern);
    }
}