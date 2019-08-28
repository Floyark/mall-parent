package com.exception;

import com.response.ServerResponse;
import com.util.ResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(UserException.class)
    public ServerResponse userExcetion(UserException ex){
        return ResponseUtil.error(ex.getMessage());
    }

}
