package com.vo;

import com.pojo.Orders;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderVO extends Orders implements Serializable {
//    private String orderId;
//    private Integer userId;
//    private String productId;
//    private java.math.BigDecimal payment;
//    private Integer quantity;
//    private Integer status;
//    private java.util.Date pruchaseDate;
    private String userName;
    private String productName;
}
