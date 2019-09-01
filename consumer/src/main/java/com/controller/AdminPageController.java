package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pojo.OrderStatus;
import com.service.OrderService;
import com.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView toCategoryTwo(){
        ModelAndView modelAndView = new ModelAndView("main/iframe/order");
        /**
         * 临时用userid,用于前端查询该id下的订单信息, 之后由登录界面session直接传到前端
         */
        modelAndView.addObject("userId",13);
        List<OrderStatus> orderStatus = orderService.getOrderStatus();
        modelAndView.addObject("status",orderStatus);

        return modelAndView;
    }

    //跳转到管理员界面
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
