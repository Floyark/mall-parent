package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
//import com.cache.UserCache;
import com.cache.UserCache;
import com.dto.UserCacheDTO;
import com.dto.UserDTO;
import com.response.ServerResponse;
import com.service.UserService;
import com.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pojo.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Reference
    UserService userService ;

    //****接收顾客信息 插入插入数据库
    @RequestMapping("/addNewUser.html")
    public @ResponseBody ServerResponse addNewUser(UserDTO userDTO,HttpSession session){
        String sessionId = (String) session.getAttribute("sessionId");
        //内存中缓存了user表 判断是否已经注册过 电话或者邮件
        List<UserCacheDTO> userCacheList = UserCache.getUserCacheList();
        for (UserCacheDTO userCacheDTO : userCacheList) {
            if(userCacheDTO.getUserEmail().equals(userDTO.getUserEmail())){
                return ResponseUtil.error("用户邮箱已经注册",60);
            }
            if(userCacheDTO.getUserPhone().equals(userDTO.getUserPhone())){
                return ResponseUtil.error("用户手机号已经注册",61);
            }
        }
        Integer userId = userService.insertNewUser(userDTO);
        if(userId==null){
            ResponseUtil.error("新增用户失败",50);
        }
        userService.setUserId(sessionId,userId);
        //唤醒缓存线程重新缓存
        UserCache.notifyCache();
        return ResponseUtil.success();
    }

}
