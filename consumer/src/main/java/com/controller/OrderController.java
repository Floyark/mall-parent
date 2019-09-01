package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.SelectOrderDTO;
import com.github.pagehelper.PageInfo;
import com.response.ServerResponse;
import com.response.TableResponse;
import com.service.OrderService;
import com.util.ResponseUtil;
import com.vo.OrderVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class OrderController {

    @Reference
    OrderService orderService;


    //将order显示到订单查询中
    @RequestMapping("/getOrderInfo")
    public @ResponseBody TableResponse getOrderInfoBySelected(SelectOrderDTO selectOrderDTO){
//        PageInfo<OrderVO> orderInfo = orderService.getOrderInfo(selectOrderDTO);
//        return new TableResponse((int)orderInfo.getTotal(),orderInfo.getList());

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId("dqwdqw");
        orderVO.setUserName("12321312");
        orderVO.setPayment(new BigDecimal(123));
        orderVO.setStatus(1);
        orderVO.setPruchaseDate(new Date());
        return new TableResponse(10,orderVO);
    }

    //购物车商品提交
    @RequestMapping("/order/insert")
    public @ResponseBody
    ServerResponse updateCart(@RequestParam(value = "context") String context){

        int userId = 13;
        //解析参数  productId和productQuantity对应
        ConcurrentHashMap<Integer, Integer> map = orderService.parseItem(context);
        //获取订单的总金额
        BigDecimal payment =orderService.getOrderPayMent(map);
        //生成新订单,返回支付二维码地址
        String qrPath =  orderService.createNewOrderId(userId,payment,map);


        return ResponseUtil.success();
    }

}
