package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.service.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pojo.User;

@Controller
public class UserController {
    @Reference
    private UserService userService;


    //游客完善信息
    @RequestMapping("/user/update")
    public String update(User user){
        int k = userService.updateUserById(user);
        if(k>0){
            return null;
        }
        return null;
    }



}

