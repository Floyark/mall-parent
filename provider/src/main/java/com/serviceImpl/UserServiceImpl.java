package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.exception.UserException;
import com.mapper.UserMapper;
import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public User findUserByPhoneNumber(String pattern) {
        User user = userMapper.getUserByPhoneNumber(pattern);
        return user;
    }

    public User findUserByEmail(String pattern) {
        User user = userMapper.getUserByEmail(pattern);
        return user;
    }

    @Transactional
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

}
