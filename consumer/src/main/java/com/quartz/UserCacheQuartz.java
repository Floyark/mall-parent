package com.quartz;

import com.cache.UserCache;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class UserCacheQuartz {

    //定时刷新 缓存的内容
    @Scheduled(cron="* 0/20 * * * ? ")
    public void userCacheQuartz(){
        System.out.println("quartz调用");
        UserCache.notifyCache();
    }

}
