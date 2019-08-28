package com.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dto.SelectOrderDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.OrderStatus;
import com.vo.OrderVO;

import java.util.List;


public interface OrderService {
    //获取order表信息
    public PageInfo<OrderVO> getOrderInfo(SelectOrderDTO selectOrderDTO);
    //获取订单状态信息(填充下拉栏)
    public List<OrderStatus> getOrderStatus();

}
