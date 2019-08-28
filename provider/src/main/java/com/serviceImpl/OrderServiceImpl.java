package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dto.SelectOrderDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.OrderMapper;
import com.pojo.OrderStatus;
import com.service.OrderService;
import com.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    public PageInfo<OrderVO> getOrderInfo(SelectOrderDTO selectOrderDTO) {
        PageHelper.startPage(selectOrderDTO.getPage(),selectOrderDTO.getLimit());
        List<OrderVO> orders= orderMapper.getOrderInfo(selectOrderDTO);

        return new PageInfo<OrderVO>(orders);
    }

    public List<OrderStatus> getOrderStatus() {
        List<OrderStatus> orderStatus = orderMapper.getOrderStatus();
        return orderStatus;
    }


}
