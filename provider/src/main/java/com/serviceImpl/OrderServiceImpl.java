package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dto.InsertOrderItemDTO;
import com.dto.QuantityDTO;
import com.dto.SelectOrderDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.OrderMapper;
import com.mapper.ProductMapper;
import com.pojo.OrderStatus;
import com.pojo.Product;
import com.service.OrderService;
import com.service.PayService;
import com.service.ProductService;
import com.vo.OrderDetailVo;
import com.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PayService payService;

    @Resource
    RedisTemplate<String,Integer>redisTemplate;

    @Autowired
    ProductMapper productMapper;

    //****根据userId获取历史订单信息
    public PageInfo<OrderVO> getOrderInfo(SelectOrderDTO selectOrderDTO) {
        PageHelper.startPage(selectOrderDTO.getPage(),selectOrderDTO.getLimit());
        List<OrderVO> orders= orderMapper.getOrderInfo(selectOrderDTO);
        return new PageInfo<OrderVO>(orders);
    }

    //****订单页面的下拉栏填充
    public List<OrderStatus> getOrderStatus() {
        List<OrderStatus> orderStatus = orderMapper.getOrderStatus();
        return orderStatus;
    }

    //****分解传来的 productId和quantity 封装成map
    public ConcurrentHashMap<Integer,Integer> parseItem(String context) {
        //分解参数
        String[] arrys= context.split(",");
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<Integer, Integer>();
        for (String arry : arrys) {
            String[] eachPart = arry.split(":");
            map.put(Integer.valueOf(eachPart[0]),Integer.valueOf(eachPart[1]));
        }
        return map;
    }

    //**** 计算订单总金额  map中是商品id 和 商品单价
    public BigDecimal getOrderPayMent(ConcurrentHashMap<Integer, Integer> map) {
        BigDecimal payment =new BigDecimal(0);
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            BigDecimal productPrice = productMapper.getProductPriceById(next.getKey());
            BigDecimal eachPayment = productPrice.multiply(new BigDecimal(next.getValue()));
            payment = payment.add(eachPayment);
        }
        return payment;
    }

    //**** 生成订单，返回二维码支付
    @Transactional
    public String createNewOrder(int userId, BigDecimal payment, ConcurrentHashMap<Integer, Integer> map) {
        //1.新建order订单号
        String orderId = UUID.randomUUID().toString().substring(0,9);
        System.out.println(orderId);
        int newOrderResult = orderMapper.insertNewOrder(orderId,userId,payment);
        if (newOrderResult!=1){
            throw new RuntimeException("新订单插入错误");
        }
        //2.新增订单条目数
        List<InsertOrderItemDTO> itemDTOList = new ArrayList<InsertOrderItemDTO>();
        Enumeration<Integer> keys = map.keys();
        while (keys.hasMoreElements()){
            Integer productId = keys.nextElement();
        //3.封装对象
            itemDTOList.add(new InsertOrderItemDTO(orderId,productId,map.get(productId)));
        }
        //4.订单详细信息插入
        int result = orderMapper.insertNewOrderItem(itemDTOList);
        if(result!=map.size()){
            throw new RuntimeException("订单项目没有插入到item表中");
        }

        String qrPath = payService.getPayQr(map);
        return qrPath;
    }

    //**** 根据用户userId orderId 获取对应订单详情
    public OrderDetailVo getOrderDetailInfo(String orderId, Integer userId) {
        return orderMapper.getOrderDetailInfo(orderId,userId);
    }

    public int checkOrder(Integer userId, String orderId) {
        Integer result = orderMapper.checkOrder(userId,orderId);
        if(result != 1 ){
            return -1;
        }
        return 1;
    }

    //**** 将productId userId 封装成map
    public ConcurrentHashMap<Integer, Integer> parseOrderDetails(String orderId) {
        List<QuantityDTO> quantityDTO = orderMapper.getOrderItem(orderId);
        ConcurrentHashMap<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<Integer, Integer>();
        for (QuantityDTO dto : quantityDTO) {
            concurrentHashMap.put(dto.getProductId(),dto.getQuantity());
        }
        return concurrentHashMap;
    }


}
