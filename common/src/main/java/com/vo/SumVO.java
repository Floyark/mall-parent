package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @BelongsProject: mall-parent
 * @BelongsPackage: com.vo
 * @Author: WisemanDong
 * @CreateTime: 2019-09-04 16:23
 * @Description: todo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SumVO {

    //总营业额
    private BigDecimal sumMoney;

}
