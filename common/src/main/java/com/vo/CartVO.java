package com.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartVO implements Serializable {

    private String productId;
    private String productName;
    private Integer productStock;
    private Integer quantity;
    private java.math.BigDecimal productPrice;

}
