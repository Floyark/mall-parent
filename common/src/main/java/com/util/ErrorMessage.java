package com.util;

import lombok.Data;
import lombok.Getter;

public enum ErrorMessage {
    PARAM_ERROR(4001,"参数错误"),
    PARAM_CODE_ERROR(4002,"验证码格式不正确"),
    EMAIL_CODE_INSERT_ERROR(5001,"根据Email插入验证码异常"),
    PHONE_CODE_INSERT_ERROR(5002,"根据Phone插入验证码异常"),
    EMAIL_SEND_ERROR(6001,"邮件发送异常"),
    MESSAGE_SEND_ERROR(6002,"短信发送异常"),
    LOGIN_STATUS_ERROR(7001,"登录状态异常"),



    ;
    @Getter
    private Integer code;
    @Getter
    private String msg;

    ErrorMessage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
