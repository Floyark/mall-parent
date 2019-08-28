package com.pojo;

import lombok.Data;


import java.io.Serializable;

@Data

public class AuthRole  implements Serializable {


	private Integer authId;

	private Integer roleId;

}
