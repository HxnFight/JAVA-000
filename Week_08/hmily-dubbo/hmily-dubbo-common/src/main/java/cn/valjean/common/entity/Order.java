package cn.valjean.common.entity;

import lombok.Data;

@Data
public class Order implements java.io.Serializable {
    private Integer id;
    private String goodsName;
    private Double price;

}
