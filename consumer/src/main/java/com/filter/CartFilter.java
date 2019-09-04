//package com.filter;
//
//import com.exception.MyException;
//import com.util.ErrorMessage;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("/cart")
//public class CartFilter implements Filter {
//
//
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String sessionId = request.getParameter("sessionId");
//        if(sessionId==null){
//            throw new MyException(ErrorMessage.LOGIN_STATUS_ERROR);
//        }
//    }
//}
