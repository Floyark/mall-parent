package com.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDetailVO implements Serializable {

    private Integer productId;
    private String productName;
    private Integer productStock;
    private String productPic;
    private Integer productStatus;
}
