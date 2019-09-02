package com.service;

import com.pojo.User;

public interface UserService {

    User findUserByPhoneNumber(String pattern);

    int updateUserById(User user);
    User findUserByEmail(String pattern);
}
