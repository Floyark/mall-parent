package com.service;

import com.pojo.User;

public interface UserService {

    User findUserByPhoneNumber(String pattern);

    User findUserByEmail(String pattern);
}
