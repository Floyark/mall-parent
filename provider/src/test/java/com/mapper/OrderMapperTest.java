package com.mapper;

import com.ProviderTest;
import com.dto.SelectOrderDTO;
import com.pojo.OrderStatus;
import com.vo.OrderVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;

import java.util.List;

import static org.junit.Assert.*;

public class OrderMapperTest extends ProviderTest {


    @Autowired
    OrderMapper orderMapper;

    @Test
    public void getOrderInfo() {

        SelectOrderDTO selectOrderDTO = new SelectOrderDTO();
        selectOrderDTO.setUserId(1);
        selectOrderDTO.setProductName("西");

        List<OrderVO> orderInfo = orderMapper.getOrderInfo(selectOrderDTO);
        System.out.println();
    }

    @Test
    public void getOrderStatus(){
        List<OrderStatus> orderStatus = orderMapper.getOrderStatus();
        System.out.println();

    }

}