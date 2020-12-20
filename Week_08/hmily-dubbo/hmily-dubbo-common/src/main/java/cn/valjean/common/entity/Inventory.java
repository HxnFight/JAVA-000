package cn.valjean.common.entity;

import lombok.Data;

@Data
public class Inventory implements java.io.Serializable {
    private Integer id;
    private String name;
    private String region;
    private Integer total;
    private Integer remind;
}
