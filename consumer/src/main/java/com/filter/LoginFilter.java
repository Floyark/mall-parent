package com.filter;

import com.exception.MyException;
import com.util.ErrorMessage;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"/sendCode","/doLogin"})
public class LoginFilter implements Filter{

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //判断验证码是否为空
        String code = httpServletRequest.getParameter("code");
        if(code!=null && !code.matches("[\\d]{6}")){
            System.out.println(code);
            throw new MyException(ErrorMessage.PARAM_CODE_ERROR);
        }
        //获取参数
        String inputAccount = httpServletRequest.getParameter("inputAccount");

        //判断参数格式是否正确
        if(inputAccount==null){
            throw new MyException(ErrorMessage.PARAM_ERROR);
        }
        if(!inputAccount.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")&&!inputAccount.matches("^1([38]\\d|5[0-35-9]|7[3678])\\d{8}$")){
            throw new MyException(ErrorMessage.PARAM_ERROR);
        }

        System.out.println("通过filter验证：" +inputAccount);
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

}
