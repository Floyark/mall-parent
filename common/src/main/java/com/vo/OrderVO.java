package com.vo;

import com.pojo.Orders;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderVO  implements Serializable {
    private String orderId;
    private java.math.BigDecimal payment;
    private Integer status;
    private java.util.Date pruchaseDate;
    private String userName;

}
