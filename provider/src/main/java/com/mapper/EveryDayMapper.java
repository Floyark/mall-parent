package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: mall-parent
 * @BelongsPackage: com.mapper
 * @Author: WisemanDong
 * @CreateTime: 2019-09-02 11:34
 * @Description: todo
 */
@Mapper
public interface EveryDayMapper {

    //统计每天的注册用户数量
    List<User> findRegisterByDate(@Param("userCreate") String userCreate);

    //统计每天的营业额
    BigDecimal findTurnoverByDate(@Param("purchaseDate") String  purchaseDate);

}
