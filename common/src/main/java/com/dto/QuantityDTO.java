package com.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class QuantityDTO implements Serializable {

    private Integer productId;
    private Integer quantity;

    public QuantityDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
