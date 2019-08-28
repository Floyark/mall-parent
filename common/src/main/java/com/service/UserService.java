package com.service;

import com.pojo.User;

public interface UserService {

    public User findUserByPhoneNumber(String pattern);

    public User findUserByEmail(String pattern);
}
