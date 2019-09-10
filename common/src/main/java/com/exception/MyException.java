package com.exception;


import com.util.ErrorMessage;
import lombok.Getter;

public class MyException extends RuntimeException{

    @Getter
    private ErrorMessage errorMessage;

    public MyException(ErrorMessage errorMessage) {
        super(errorMessage.getMsg());
    }
    public MyException(String str){
        super(str);
    }


}
