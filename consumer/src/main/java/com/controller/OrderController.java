package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.SelectOrderDTO;
import com.github.pagehelper.PageInfo;
import com.response.ServerResponse;
import com.response.TableResponse;
import com.service.CartService;
import com.service.OrderService;
import com.util.ResponseUtil;
import com.vo.OrderVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class OrderController {

    @Reference
    OrderService orderService;
    @Reference
    CartService cartService;

    //****将order显示到订单查询中
    @RequestMapping("/getOrderInfo")
    public @ResponseBody TableResponse getOrderInfoBySelected(SelectOrderDTO selectOrderDTO,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        selectOrderDTO.setUserId(userId);
        PageInfo<OrderVO> orderInfo = orderService.getOrderInfo(selectOrderDTO);
        return new TableResponse((int)orderInfo.getTotal(),orderInfo.getList());
    }

    //****购物车商品提交
    @RequestMapping("/order/insert")
    public @ResponseBody
    ServerResponse updateCart(@RequestParam(value = "context") String context, HttpSession session){

        int userId = (Integer) session.getAttribute("userId");
        //解析参数  productId和productQuantity对应
        ConcurrentHashMap<Integer, Integer> map = orderService.parseItem(context);
        //获取订单的总金额
        BigDecimal payment =orderService.getOrderPayMent(map);
        //生成新订单,返回支付二维码地址
        String qrPath =  orderService.createNewOrder(userId,payment,map);
        //将redis中的数据全部清除
        boolean result = cartService.clearRedisCache(userId);
        return ResponseUtil.successWithData(qrPath);
    }

}
