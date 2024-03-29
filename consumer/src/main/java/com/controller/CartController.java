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
        if(userId<0){
            ModelAndView modelAndView = new ModelAndView("main/iframe/addUserInfo.html");
            if(-userId%2==0){
                //用户id单复数，判断用户是邮件登录(负数)   还是手机号登录（单数）
                System.out.println("判断进入邮件email");
                String email = userService.getUserIdByEmail(-userId);
                System.out.println("游客id为"+userId+":"+email);
                modelAndView.addObject("email",email);
            }else{
                System.out.println("判断进入手机号phone");
                String phoneNumber = userService.getUserIdByPhone(-userId);
                System.out.println("游客id为"+userId+":"+phoneNumber);
                modelAndView.addObject("phone",phoneNumber);
            }
            return modelAndView;
        }
        //获取购物车商品 id 和 购买数量
        HashMap<Integer,Integer> cartItem = cartService.getCartItem(userId);
        if(cartItem==null){
            return new ModelAndView("/main/iframe/cart_no_info.html");
        }
        //根据map中的商品id获取对应的商品，封装成CartVO
        List<CartVO> cartVOList = productService.getCartProductInfo(cartItem);
        ModelAndView modelAndView = new ModelAndView("main/iframe/cart.html");
        modelAndView.addObject("list",cartVOList);
        return modelAndView;
    }

    //商品详情页面 加入购物车
    @RequestMapping("/cart/put")
    public @ResponseBody ServerResponse putInCart(CartDTO cartDTO, HttpSession session){

        String sessionId = (String) session.getAttribute("sessionId");
        Integer userId = userService.getUserId(sessionId);
        if(userId==null){
            return ResponseUtil.error("userId没有找到，重新登录",10);
        }
        if(userId<0){
            return ResponseUtil.error("需要完善信息",20);
        }
        cartDTO.setUserId(userId);
        int result = cartService.putProductInRedis(cartDTO);
        if(result!=1){
            return ResponseUtil.error("添加失败",0);
        }
        return ResponseUtil.success();
    }


}
