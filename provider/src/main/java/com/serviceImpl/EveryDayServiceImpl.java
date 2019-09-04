package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.form.UserForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.EveryDayMapper;
import com.pojo.User;
import com.service.EveryDayService;
import com.vo.LayUITableVO;
import com.vo.SumVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: mall-parent
 * @BelongsPackage: com.serviceImpl
 * @Author: WisemanDong
 * @CreateTime: 2019-09-03 11:26
 * @Description: todo
 */

@Service
public class EveryDayServiceImpl implements EveryDayService {

    @Autowired
    private EveryDayMapper everyDayMapper;

    @Override
    public LayUITableVO<User> findRegisterByDate(UserForm userForm) {

        PageHelper.startPage(userForm.getPage(),userForm.getLimit());
        //2.调用dao查询数据
        List<User> userList = everyDayMapper.findRegisterByDate(userForm);
        //3.封装PageInfo
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        //4.封装LayUITableVO
        LayUITableVO<User> layUITableVO = new LayUITableVO<>();
        layUITableVO.setCount(pageInfo.getTotal());
        layUITableVO.setData(pageInfo.getList());
        return layUITableVO;
    }

    @Override
    public SumVO findTurnoverByDate(String purchaseDate) {
        BigDecimal bigDecimal = everyDayMapper.findTurnoverByDate(purchaseDate);
        SumVO sumVO = new SumVO();
        sumVO.setSumMoney(bigDecimal);
        return sumVO;
    }

}
