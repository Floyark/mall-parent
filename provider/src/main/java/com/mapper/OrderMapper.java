package com.mapper;

import com.dto.InsertOrderItemDTO;
import com.dto.SelectOrderDTO;
import com.pojo.OrderStatus;
import com.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mapper
public interface OrderMapper {

    //根据用户的id 商品名 订单状态查询(填充主表)
    public List<OrderVO> getOrderInfo(SelectOrderDTO selectOrderDTO);

    //获取支付状态(用于填充搜索界面的下拉框)
    public List<OrderStatus> getOrderStatus();

    int insertNewOrder(@Param(value = "orderId") String orderId, @Param(value = "userId")int userId,@Param(value = "payment") BigDecimal payment);

    int insertNewOrderItem(List<InsertOrderItemDTO> list);
}
