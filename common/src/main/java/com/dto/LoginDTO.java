package com.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
//
@Data
public class LoginDTO implements Serializable {


    private Integer userId;

    @NotEmpty
    private String inputAccount;
    @NotEmpty
    private String code;

}
