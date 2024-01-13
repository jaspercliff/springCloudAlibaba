package com.jasper.bean;

import lombok.Data;

@Data
public class Orders {
    private Integer id;

    private Integer userId;

    private Integer goodsId;
    private double goodsPrice;
    private Integer count;

}
