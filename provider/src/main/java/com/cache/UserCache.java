//package com.cache;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.dto.UserCacheDTO;
//import com.service.UserService;
//import com.serviceImpl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class UserCache implements Runnable{
//
//    private static List<UserCacheDTO> list = new ArrayList<UserCacheDTO>();
//    private static int count = 0;
//
//    @Reference
//    UserServiceImpl userService;
//
////    @PostConstruct
////    public void init(){//利用spring bean的生命周期  初始化bean之后执行
////        new Thread(this).start();//启动当前线程
////    }
//
//
//    public void run() {
//
//        List<UserCacheDTO> userCacheInfo = userService.getUserCacheInfo();
//        //数据库查询的 数据 放到 静态变量里
//        list.addAll(userCacheInfo);
//        System.out.println(Thread.currentThread().getName()+" : 第"+(++count)+"次更新缓存");
//        try {
//            Thread.sleep(1000*60);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static List<UserCacheDTO> getUserCacheList(){
//        return list;
//    }
//}
