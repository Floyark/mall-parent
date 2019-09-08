package com.vo;

import com.dto.productInfoForOrderDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDetailVo {
    //订单id，订单用户名，订单用户id，下单时间
    private String orderId;
    private Integer userId;
    private String userName;
    private Date pruchase_date;
    private List<productInfoForOrderDTO> products;


    /**
     *     private Integer productId;
     *     private String productName;
     *     private Integer quantity;
     */
}
