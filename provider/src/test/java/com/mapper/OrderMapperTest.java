package com.mapper;

import com.ProviderTest;
import com.dto.InsertOrderItemDTO;
import com.dto.QuantityDTO;
import com.dto.SelectOrderDTO;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.pojo.OrderStatus;
import com.vo.OrderDetailVo;
import com.vo.OrderVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderMapperTest extends ProviderTest {


    @Autowired
    OrderMapper orderMapper;

    @Test
    public void getOrderInfo() {

        SelectOrderDTO selectOrderDTO = new SelectOrderDTO();
        selectOrderDTO.setUserId(3);
        selectOrderDTO.setOrderStatus(0);
        List<OrderVO> orderInfo = orderMapper.getOrderInfo(selectOrderDTO);
        for (OrderVO orderVO : orderInfo) {
            System.out.println(orderVO.toString());
        }
    }

    @Test
    public void getOrderStatus(){
        List<OrderStatus> orderStatus = orderMapper.getOrderStatus();
        System.out.println();

    }

    @Test
    public void insertNewOrderItem() {

        String orderId = "userId123";
        List list = new ArrayList();
        list.add(new InsertOrderItemDTO(orderId,1,11));
        list.add(new InsertOrderItemDTO(orderId,2,22));
        list.add(new InsertOrderItemDTO(orderId,3,33));
        list.add(new InsertOrderItemDTO(orderId,4,44));
        list.add(new InsertOrderItemDTO(orderId,5,55));
        int i = orderMapper.insertNewOrderItem(list);
        System.out.println(i);

    }

    @Test
    public void insertNewOrder(){
        String orderId = "orderId";
        int i = orderMapper.insertNewOrder(orderId, 3, new BigDecimal(270));
        System.out.println(i);
    }

    @Test
    public void getOrderDetailInfo() {

        int userId = 3;
        String orderId  = "214551a4-";

        OrderDetailVo orderDetailInfo = orderMapper.getOrderDetailInfo(orderId, userId);

            System.out.println(orderDetailInfo.toString());
    }

    @Test
    public void getOrderItem() {
        List<QuantityDTO> orderItem = orderMapper.getOrderItem("12bd8a41-");
        for (QuantityDTO quantityDTO : orderItem) {
            System.out.println(quantityDTO.toString());
        }
    }
}