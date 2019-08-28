package com.pojo;

import lombok.Data;


import java.io.Serializable;

@Data

public class RoleUser  implements Serializable {

	private Integer roleId;


	private Integer userId;

}
