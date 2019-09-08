package com.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private Integer userId;
    private String userName;
    private String userPhone;
    private String userAddress;
    private String userEmail;


}
