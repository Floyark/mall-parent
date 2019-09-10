package com.cache;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.UserCacheDTO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserCache implements Runnable{

    private static List<UserCacheDTO> list = new ArrayList<UserCacheDTO>();
    private static int count = 0;
    private final static String LOCK="";

    @Reference
    UserService userService;

    @PostConstruct
    public void init(){//利用spring bean的生命周期  初始化bean之后执行
        new Thread(this).start();//启动当前线程
    }


    public void run() {
        List<UserCacheDTO> userCacheInfo = null;

        while (true){
            synchronized (LOCK) {
                userCacheInfo = userService.getUserCacheInfo();
                //数据库查询的 数据 放到 静态变量里
                list.addAll(userCacheInfo);
                System.out.println("进行第" + (++count) + "次循环");
                try {
//                   Thread.sleep(1000*60);
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<UserCacheDTO> getUserCacheList(){
        return list;
    }

    public static void notifyCache(){
        synchronized (LOCK){
            LOCK.notify();
        }
    }

}
