package com.vo;

import com.dto.productInfoForOrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDetailVo implements Serializable {
    //订单id，订单用户名，订单用户id，下单时间
    private String orderId;
    private Integer userId;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date pruchase_date;
    private BigDecimal payment;
    private List<productInfoForOrderDTO> products;


    /**
     *     private Integer productId;
     *     private String productName;
     *     private Integer quantity;
     *     private BigDecimal price;
     */
}
