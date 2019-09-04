package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.service.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pojo.User;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping("/addNewUser.html")
    public ModelAndView addNewUser(){

        ModelAndView modelAndView = new ModelAndView("main/iframe/addNewProduct");
        return modelAndView;
    }

}
