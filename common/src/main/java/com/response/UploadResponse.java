package com.response;

import lombok.Data;

@Data
public class UploadResponse<T> {

//    {
//        "code": 0
//            ,"msg": ""
//            ,"data": {
//               "src": "http://cdn.layui.com/123.jpg"
//              }
//    }


    private Integer code = 0;
    private String msg;
    private T data;

    public UploadResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
