package com.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MainTableDTO implements Serializable {


    private String productName;
    private Integer productStatus;
    private Integer page;
    private Integer limit;
}
