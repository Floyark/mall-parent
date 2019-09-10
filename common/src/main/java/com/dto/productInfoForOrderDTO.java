package com.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class productInfoForOrderDTO implements Serializable {

    private Integer productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
