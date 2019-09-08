package com.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dto.SelectOrderDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.OrderStatus;
import com.vo.OrderDetailVo;
import com.vo.OrderVO;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public interface OrderService {
    //####获取order表信息
    PageInfo<OrderVO> getOrderInfo(SelectOrderDTO selectOrderDTO);
    //####获取订单状态信息(填充下拉栏)
    List<OrderStatus> getOrderStatus();

    ConcurrentHashMap<Integer,Integer> parseItem(String context);

    BigDecimal getOrderPayMent(ConcurrentHashMap<Integer, Integer> map);

    String createNewOrder(int userId, BigDecimal payment, ConcurrentHashMap<Integer, Integer> map);

    List<OrderDetailVo> getOrderDetailInfo(String orderId, Integer userId);
}
