package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.form.UserForm;
import com.pojo.User;
import com.service.EveryDayService;
import com.vo.LayUITableVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: mall-parent
 * @BelongsPackage: com.controller
 * @Author: WisemanDong
 * @CreateTime: 2019-09-03 16:44
 * @Description: todo
 */
@Controller
public class EveryDayController {

    @Reference
    EveryDayService everyDayService;

    private Date DATE;

    @GetMapping("/monlist")
    @ResponseBody
    public LayUITableVO everyDaySum( UserForm userForm){
        //DATE=searchDate;
        //System.out.println(searchDate);
        //@DateTimeFormat(pattern = "yyyy-MM-dd")Date searchDate,
        LayUITableVO vo = everyDayService.findRegisterByDate(new Date().toString(), userForm);


        return vo;
    }
    @PostMapping("/sum")
    public String getSum(@DateTimeFormat(pattern = "yyyy-MM-dd")Date searchDate,Model model){
        BigDecimal sumMoney = everyDayService.findTurnoverByDate(searchDate.toString());
        model.addAttribute("sumMoney",sumMoney);
        return "sum";
    }
    @GetMapping("/every")
    public String toEvery(){
        return "/main/iframe/every";
    }


}
