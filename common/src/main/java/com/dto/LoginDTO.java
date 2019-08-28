package com.dto;

import lombok.Data;

import java.io.Serializable;
//
@Data
public class LoginDTO implements Serializable {

    private String phoneNumber;
    private String code;

}
