package com.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.CartDTO;
import com.exception.MyException;
import com.pojo.Product;
import com.response.ServerResponse;
import com.service.CartService;
import com.service.OrderService;
import com.service.ProductService;
import com.service.UserService;
import com.util.ResponseUtil;
import com.vo.CartVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    @Reference
    UserService userService;

    //展示购物车界面
    @RequestMapping("/cart.html")
    public ModelAndView getProductInfo(HttpSession httpSession){
        String sessionId = (String) httpSession.getAttribute("sessionId");
        Integer userId = userService.getUserId(sessionId);
        if(userId==null){
            return new ModelAndView("login/login.html");
        }
        if(userId==0){
            return new ModelAndView("main/iframe/addUserInfo.html");
        }
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
    public @ResponseBody ServerResponse putInCart(CartDTO cartDTO, HttpSession session){

        String sessionId = (String) session.getAttribute("sessionId");
        Integer userId = userService.getUserId(sessionId);
        if(userId==null){
            return ResponseUtil.error("userId没有找到，重新登录");
        }
        cartDTO.setUserId(userId);
        int result = cartService.putProductInRedis(cartDTO);
        if(result!=1){
            return ResponseUtil.error("添加失败");
        }
        return ResponseUtil.success();
    }


}
