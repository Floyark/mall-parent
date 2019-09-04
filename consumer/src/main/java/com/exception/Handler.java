package com.exception;

import com.response.ServerResponse;
import com.util.JsonUtil;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Handler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        this.hander(response,ex);
        return null;
    }

    public static void hander(HttpServletResponse response,Exception ex){
        ServerResponse serverResponse = new ServerResponse();
        if(ex instanceof MyException){
            MyException myException = (MyException) ex;
            serverResponse.setCode(myException.getErrorMessage().getCode());
            serverResponse.setMsg(myException.getErrorMessage().getMsg());
            serverResponse.setData(null);
        }
        String json = JsonUtil.getJSON(serverResponse);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(json);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(printWriter!=null){
                printWriter.close();
            }
        }
    }
}
