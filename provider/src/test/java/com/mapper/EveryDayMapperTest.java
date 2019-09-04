package com.mapper;

import com.ProviderTest;
import com.form.UserForm;
import com.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: mall-parent
 * @BelongsPackage: com.mapper
 * @Author: WisemanDong
 * @CreateTime: 2019-09-02 15:43
 * @Description: todo
 */
public class EveryDayMapperTest extends ProviderTest {

    @Autowired
    private EveryDayMapper everyDayMapper;

    @Test
    public void findRegisterByDate() {

        UserForm userForm = new UserForm();
        userForm.setLimit(1);
        userForm.setPage(1);
        userForm.setUserCreate("2019-09-04");
        List<User> count = everyDayMapper.findRegisterByDate(userForm);
        for (User user : count) {
            System.out.println(user.toString());
        }

    }

    @Test
    public void findTurnoverByDate(){

        //new SimpleDateFormat("yyyy-MM-dd").format(new Date(2019,9,1))
        //new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-01")
        //BigDecimal sum = everyDayMapper.findTurnoverByDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-09-01"));
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-09-01"));

        //Date d = new Date(2019,9,1);
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String str = sdf.format(d);
        //System.out.println(str);
        BigDecimal sumMoney = everyDayMapper.findTurnoverByDate(null);
        System.out.println(sumMoney);
    }
}