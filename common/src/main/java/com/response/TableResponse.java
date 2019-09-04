package com.response;

import lombok.Data;

import javax.swing.*;

@Data
public class TableResponse<T> {

//
//    {
//        "code": 0,
//            "msg": "",
//            "count": 1000,
//            "data": [{}, {}]
//    }

    private Integer code =0;
    private String msg="success";
    private long count;
    private T data;

    public TableResponse(long count, T data) {
        this.count = count;
        this.data = data;
    }
}
