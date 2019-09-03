package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: app
 * @BelongsPackage: com.qf.app.vo
 * @Author: WisemanDong
 * @CreateTime: 2019-08-08 10:19
 * @Description: todo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayUITableVO<T> implements Serializable {

    private Integer code = 0;

    private String msg = "";

    private Long count;

    private List<T> data;

}
