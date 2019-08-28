package com.pojo;
import lombok.Data;

import java.io.Serializable;

@Data

public class Product  implements Serializable {

	private Integer productId;

	private String productName;


	private Integer procuctStock;


	private java.math.BigDecimal productPrice;

}
