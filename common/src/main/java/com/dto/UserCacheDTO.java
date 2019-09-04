package com.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCacheDTO implements Serializable {

    private Integer userId;
    private String userPhone;
    private String userEmail;
}
