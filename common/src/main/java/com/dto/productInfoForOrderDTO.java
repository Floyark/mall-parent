package com.dto;

import lombok.Data;

@Data
public class productInfoForOrderDTO {

    private Integer productId;
    private String productName;
    private Integer quantity;
}
