package com.dto;


import lombok.Data;

@Data
public class QuantityDTO {

    private Integer productId;
    private Integer quantity;

    public QuantityDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
