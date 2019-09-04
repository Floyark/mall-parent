package com.dto;

import lombok.Data;

@Data
public class InsertOrderItemDTO {

    private String orderId;
    private Integer productId;
    private Integer productQuantity;

    public InsertOrderItemDTO(String orderId, Integer productId, Integer productQuantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
