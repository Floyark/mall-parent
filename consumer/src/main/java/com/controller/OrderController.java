package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.SelectOrderDTO;
import com.exception.MyException;
import com.github.pagehelper.PageInfo;
import com.response.ServerResponse;
import com.response.TableResponse;
import com.service.CartService;
import com.service.OrderService;
import com.service.UserService;
import com.util.ResponseUtil;
import com.vo.OrderDetailVo;
import com.vo.OrderVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class OrderController {

    @Reference
    OrderService orderService;
    @Reference
    CartService cartService;
    @Reference
    UserService userService;

    //****将order显示到订单查询中
    @RequestMapping("/getOrderInfo")
    public @ResponseBody TableResponse getOrderInfoBySelected(SelectOrderDTO selectOrderDTO,HttpSession session){
        String sessionId = (String)session.getAttribute("sessionId");
        Integer userId = userService.getUserId(sessionId);
        selectOrderDTO.setUserId(userId);
        PageInfo<OrderVO> orderInfo = orderService.getOrderInfo(selectOrderDTO);
        return new TableResponse((int)orderInfo.getTotal(),orderInfo.getList());
    }

    //****购物车商品提交
    @RequestMapping("/order/insert")
    public @ResponseBody
    ServerResponse insertCart(@RequestParam(value = "context") String context, HttpSession session){

        String sessionId = (String) session.getAttribute("sessionId");
        Integer userId = userService.getUserId(sessionId);
        //解析参数  productId和productQuantity对应
        ConcurrentHashMap<Integer, Integer> map = orderService.parseItem(context);
        //获取订单的总金额
        BigDecimal payment =orderService.getOrderPayMent(map);
        //生成新订单,返回支付二维码地址
        String qrPath =  orderService.createNewOrder(userId,payment,map);
        System.out.println("生成的二维码地址："+qrPath);
        //urlencode
        String encode = URLEncoder.encode(qrPath);
        //将redis中的数据全部清除
        boolean result = cartService.clearRedisCache(userId);
        return ResponseUtil.successWithData(encode);
    }

    //****查询orderId内的详细商品信息
    @RequestMapping("/orderDetail.html")
    public ModelAndView orderDetail(String orderId,HttpSession session){
        //1、检查登录用户和orderId是否对应
        String sessionId = session.getId();
        Integer userId = userService.getUserId(sessionId);
        if(userId==null){
            throw new MyException("用户登录状态异常");
        }
        List<OrderDetailVo> orderDetailVoList = orderService.getOrderDetailInfo(orderId,userId);
        if(orderDetailVoList==null){
            throw new MyException("用户和订单信息不匹配");
        }
        ModelAndView modelAndView = new ModelAndView("main/iframe/orderDetail.html");
        //查询出数据后填充 todo

        return modelAndView;
    }
}
