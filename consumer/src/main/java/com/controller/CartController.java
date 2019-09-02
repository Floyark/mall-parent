package com.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.CartDTO;
import com.pojo.Product;
import com.response.ServerResponse;
import com.service.CartService;
import com.service.OrderService;
import com.service.ProductService;
import com.util.ResponseUtil;
import com.vo.CartVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.event.InternalFrameEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class CartController {


    @Reference
    CartService cartService;
    @Reference
    ProductService productService;

    @RequestMapping("/cart.html")
    public ModelAndView getProductInfo(HttpSession session){
        //之后通过session获取
        Integer userId = (Integer) session.getAttribute("userId");
        //获取购物车商品 id 和 购买数量
        HashMap<Integer,Integer> cartItem = cartService.getCartItem(userId);
        if(cartItem==null){
            return new ModelAndView("/main/iframe/cart_no_info.html");
        }
        //根据map中的商品id获取对应的商品，封装成CartVO
        List<CartVO> cartVOList = productService.getCartProductInfo(cartItem);
        ModelAndView modelAndView=new ModelAndView("main/iframe/cart.html");
        modelAndView.addObject("list",cartVOList);
        return modelAndView;
    }


    //商品详情页面 加入购物车
    @RequestMapping("/cart/put")
    public @ResponseBody ServerResponse putInCart(CartDTO cartDTO,HttpSession session){
        int userId = (Integer) session.getAttribute("userId");
        cartDTO.setUserId(userId);
        int result = cartService.putProductInRedis(cartDTO);
        if(result!=1){
            return ResponseUtil.error("添加失败");
        }
        return ResponseUtil.success();
    }



}
