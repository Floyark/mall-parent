package com.response;

import lombok.Data;

/**
 * 用于controller向前端传递的对象
 * @param <T>
 */
@Data
public class ServerResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public ServerResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ServerResponse(){

    }
}
