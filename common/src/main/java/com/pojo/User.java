package com.pojo;

import lombok.Data;


import java.io.Serializable;

@Data

public class User  implements Serializable {


	private Integer userId;

	private String userName;


	private String userPhone;

	private String userAddress;


	private String userCode;

	private java.util.Date userCreate;


	private Integer userStatus;

	private String userEmail;
}
