package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;

@Data
public class Orders  implements Serializable {


	private String orderId;

	private Integer userId;

	private java.math.BigDecimal payment;

	private Integer status;

	@JsonFormat(pattern = "yyyy--MM--dd")
	private java.util.Date pruchaseDate;

}
