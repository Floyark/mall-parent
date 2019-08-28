package com.pojo;

import lombok.Data;


import java.io.Serializable;

@Data
public class OrderStatus  implements Serializable {


	private Integer orderStatusId;


	private String orderStatusName;

}
