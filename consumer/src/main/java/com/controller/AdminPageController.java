package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pojo.OrderStatus;
import com.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminPageController {


    @Reference
    OrderService orderService;

    @RequestMapping("/mainPage")
    public ModelAndView toMain(){

        return new ModelAndView("main/admin_index");
    }

    //跳转到分类一
    @RequestMapping("/category_one.html")
    public ModelAndView toCategoryOne(){

        return new ModelAndView("main/iframe/cate_one");
    }

    //跳转到分类二
    @RequestMapping("/order.html")
    public ModelAndView toCategoryTwo(Model model){
        ModelAndView modelAndView = new ModelAndView("main/iframe/order");
        /**
         * 临时用userid,用于前端查询该id下的订单信息, 之后由登录界面session直接传到前端
         */
        model.addAttribute("userId",13);
        List<OrderStatus> orderStatus = orderService.getOrderStatus();
        model.addAttribute("status",orderStatus);

        return modelAndView;
    }
}
