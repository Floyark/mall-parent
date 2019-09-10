package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.LoginDTO;
import com.exception.MyException;
import com.response.ServerResponse;
import com.service.UserService;
import com.util.ErrorMessage;
import com.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {


    @Reference
    UserService userService;

    //****跳转到登录界面
    @RequestMapping("/login.html")
    public String toLogin(){
        return "login/login";
    }

    //****根据用户输入的参数，发送验证码
    @RequestMapping("/sendCode")
    public @ResponseBody ServerResponse sendCode(String inputAccount){
        //校验参数
        if(inputAccount==""){
            throw new MyException(ErrorMessage.PARAM_ERROR);
        }
        //发送邮箱验证
        if(inputAccount.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
            userService.sendCodeByEmail(inputAccount);
        }
        //通过手机短信验证
        else if(inputAccount.matches("^1([38]\\d|5[0-35-9]|7[3678])\\d{8}$")){
            userService.sendCodeByPhone(inputAccount);
        }
        //邮箱和手机的形式都不匹配
        else{
            return  ResponseUtil.error("重新输入,参数有误",40);
        }
        return ResponseUtil.successWithMsg("验证码已经发送~");
    }

    //****进行登录的验证 比对验证码和输入的信息
    @RequestMapping("/doLogin")
    public ModelAndView doLogin(@Valid LoginDTO loginDTO, BindingResult result, HttpSession session){
        //校验参数是否为空
        if(result.hasErrors()){
            return new ModelAndView("login/login");
        }
        //校验账户是邮箱地址还是手机号
        Boolean isEmail  = loginDTO.getInputAccount().matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        //判断验证码和登录名是否匹配，并且返回userId，0为游客
        Integer userId = userService.checkLoginParam(loginDTO,isEmail);
        if(userId==-1){
            return new ModelAndView("login/login");
        }
        String sessionId = session.getId();
        userService.setUserId(sessionId,userId);
        session.setAttribute("sessionId",sessionId);
        ModelAndView modelAndView = new ModelAndView("main/iframe/main");
        return modelAndView;
    }

}
