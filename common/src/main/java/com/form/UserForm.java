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

    private Integer page;

    private Integer limit;

    private String userCreate;

}
