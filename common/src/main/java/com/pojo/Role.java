package com.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Role  implements Serializable {


	private Integer roleId;


	private String roleName;

	private String roleDescribe;

}
