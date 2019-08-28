package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.LoginDTO;
import com.pojo.User;
import com.response.ServerResponse;
import com.service.UserService;
import com.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @Reference
    UserService userService;

    //跳转到登录界面
    @RequestMapping("/login")
    public String toLogin(){
        return "login/login";
    }


    @RequestMapping("/sendCode")
    public @ResponseBody ServerResponse sendCode(String pattern){
        User user=null;
        if(pattern==""){
            return ResponseUtil.error("参数错误,重新输入");
        }

        if(pattern.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
            //通过邮箱查询
            user = userService.findUserByEmail(pattern);


        }else if(pattern.matches("^1([38]\\d|5[0-35-9]|7[3678])\\d{8}$")){
            //通过手机查询
            user = userService.findUserByPhoneNumber(pattern);
        }else{
            return  ResponseUtil.error("重新输入,参数有误");
        }



        return ResponseUtil.success();
    }

    //转到login界面
    @RequestMapping("/doLogin")
    public ModelAndView doLogin(LoginDTO loginDTO){

        //todo   根据shiro校验结果，选择不同的页面跳转

        return null;
    }

}
