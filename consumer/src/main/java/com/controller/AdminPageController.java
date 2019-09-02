package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pojo.OrderStatus;
import com.service.OrderService;
import com.service.ProductService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminPageController {

    @Reference
    ProductService productService;
    @Reference
    OrderService orderService;


    //跳转到分类一
    @RequestMapping("/main.html")
    public ModelAndView toCategoryOne(){
        return new ModelAndView("main/iframe/main");
    }

    //跳转到订单页面
    @RequestMapping("/order.html")
    public ModelAndView toCategoryTwo(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("main/iframe/order");
        int userId = (Integer) session.getAttribute("userId");
        List<OrderStatus> orderStatus = orderService.getOrderStatus();
        modelAndView.addObject("status",orderStatus);

        return modelAndView;
    }

    //跳转到管理员界面
    @RequestMapping("/admin.html")
    public ModelAndView toAdminPage(HttpSession session){
        session.setAttribute("userId",19);
        session.setMaxInactiveInterval(3600);
        ModelAndView modelAndView = new ModelAndView("main/admin_index");
        return modelAndView;
    }

    @RequestMapping("/addNewProduct.html")
    public ModelAndView toAddNewProduct(){
        ModelAndView modelAndView = new ModelAndView("main/iframe/addNewProduct.html");
        return modelAndView;
    }


}
