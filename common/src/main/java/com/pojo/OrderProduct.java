package com.pojo;

import lombok.Data;

import java.io.Serializable;

@Data

public class OrderProduct  implements Serializable {


	private String orderId;

	private Integer productId;

}
