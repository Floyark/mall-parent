package com.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: mall-parent
 * @BelongsPackage: com.form
 * @Author: WisemanDong
 * @CreateTime: 2019-09-03 20:50
 * @Description: todo
 */
@Data
public class UserForm implements Serializable {

    private Integer userId;

    private String userName;


    private String userPhone;


    private String userAddress;


    private String userCode;

    private java.util.Date userCreate;


    private Integer userStatus;

    private String userEmail;

    private Integer limit;

    private Integer page;
}
