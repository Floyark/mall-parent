package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.io.Serializable;

@Data
public class Orders  implements Serializable {


	private String orderId;

	private Integer userId;

	private String productId;

	private java.math.BigDecimal payment;

	private Integer quantity;

	private Integer status;

	@JsonFormat(pattern = "yyyy--MM--dd")
	private java.util.Date pruchaseDate;

}
