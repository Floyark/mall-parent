package com.service;

import com.ProviderTest;
import com.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * @BelongsProject: mall-parent
 * @BelongsPackage: com.service
 * @Author: WisemanDong
 * @CreateTime: 2019-09-03 15:37
 * @Description: todo
 */
public class EveryDayServiceTest extends ProviderTest {

    @Autowired
    private EveryDayService everyDayService;

    //@Test
    //public void findRegisterByDate() {
    //
    //    List<User> list = null;
    //    String primary = "2019-08-25 1:2:3";
    //    String pattern = "yyyy-MM-dd hh:mm:ss";
    //    String fmt = "yyyy-MM-dd";
    //
    //    try {
    //        list = everyDayService.findRegisterByDate(new SimpleDateFormat(fmt).format(new SimpleDateFormat(pattern).parse(primary)));
    //    } catch (ParseException e) {
    //        e.printStackTrace();
    //    }
    //    for (User user : list) {
    //        System.out.println(user.toString());
    //    }
    //
    //}
    //
    //@Test
    //public void findTurnoverByDate() {
    //
    //    BigDecimal sumMoney = everyDayService.findTurnoverByDate("");
    //    System.err.println(sumMoney);
    //
    //}
}