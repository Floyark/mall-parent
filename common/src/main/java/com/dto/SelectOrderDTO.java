package com.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectOrderDTO implements Serializable {

    private Integer userId;
    private Integer orderStatus;
    private Integer page;
    private Integer limit;
}
