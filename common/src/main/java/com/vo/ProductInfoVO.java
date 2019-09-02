package com.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Principal;

@Data
public class ProductInfoVO implements Serializable {

    private Integer productId;
    private BigDecimal productPrice;
    private String productPic;
    private String productName;
}
