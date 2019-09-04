package com.service;

import com.form.UserForm;
import com.pojo.User;
import com.vo.LayUITableVO;
import com.vo.SumVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: mall-parent
 * @BelongsPackage: com.service
 * @Author: WisemanDong
 * @CreateTime: 2019-09-03 11:28
 * @Description: todo
 */
public interface EveryDayService {

    //统计每天的注册用户数量
    LayUITableVO<User> findRegisterByDate(UserForm userForm);

    //统计每天的营业额
    SumVO findTurnoverByDate(String  purchaseDate);

}
