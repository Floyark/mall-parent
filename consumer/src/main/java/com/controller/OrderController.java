package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.SelectOrderDTO;
import com.github.pagehelper.PageInfo;
import com.response.TableResponse;
import com.service.OrderService;
import com.vo.OrderVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class OrderController {

    @Reference
    OrderService orderService;

    @RequestMapping("/getOrderInfo")
    public @ResponseBody TableResponse getOrderInfoBySelected(SelectOrderDTO selectOrderDTO){
//        PageInfo<OrderVO> orderInfo = orderService.getOrderInfo(selectOrderDTO);
//        return new TableResponse((int)orderInfo.getTotal(),orderInfo.getList());

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId("dqwdqw");
        orderVO.setUserName("12321312");
        orderVO.setQuantity(19);
        orderVO.setPayment(new BigDecimal(123));
        orderVO.setStatus(1);
        orderVO.setPruchaseDate(new Date());
        return new TableResponse(10,orderVO);
    }

}
