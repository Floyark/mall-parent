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

@Controller
public class OrderController {

    @Reference
    OrderService orderService;

    @RequestMapping("/getOrderInfo")
    public @ResponseBody TableResponse getOrderInfoBySelected(SelectOrderDTO selectOrderDTO){
        PageInfo<OrderVO> orderInfo = orderService.getOrderInfo(selectOrderDTO);
        return new TableResponse((int)orderInfo.getTotal(),orderInfo.getList());
    }

}
