package com.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartDTO implements Serializable {

    private Integer userId;
    private Integer productId;
    private Integer productQuantity;
}
