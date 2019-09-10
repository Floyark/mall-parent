package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pojo.OrderStatus;
import com.service.OrderService;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminPageController {

    @Reference
    ProductService productService;
    @Reference
    OrderService orderService;
    @Reference
    UserService userService;

    @RequestMapping("/main.html")
    public ModelAndView toMain(HttpSession session){
        String sessionId = session.getId();
        Integer userId = userService.getUserId(sessionId);
        if(userId==null){
            return new ModelAndView("login/login");
        }
        ModelAndView modelAndView =new ModelAndView("main/iframe/main");
        return modelAndView;
    }

    //****订单页面的下拉栏填充
    @RequestMapping("/order.html")
    public ModelAndView toOrder(){
        ModelAndView modelAndView = new ModelAndView("main/iframe/order");
        List<OrderStatus> orderStatus = orderService.getOrderStatus();
        modelAndView.addObject("status",orderStatus);
        return modelAndView;
    }

    @RequestMapping("/admin.html")
    public ModelAndView toAdminPage(){

        ModelAndView modelAndView = new ModelAndView("main/admin_index");
        return modelAndView;
    }

    @RequestMapping("/addNewProduct.html")
    public ModelAndView toAddNewProduct(){
        ModelAndView modelAndView = new ModelAndView("main/iframe/addNewProduct.html");
        return modelAndView;
    }


}
