package com.controller;

import com.response.ServerResponse;
import com.sun.net.httpserver.Authenticator;
import com.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayController {


    @RequestMapping("/payment")
    public ServerResponse payment(){
        //1、获取paymentId;


        return ResponseUtil.success();
    }

}
