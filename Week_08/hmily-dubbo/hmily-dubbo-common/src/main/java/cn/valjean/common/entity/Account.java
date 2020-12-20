package cn.valjean.common.entity;

import lombok.Data;

@Data
public class Account implements java.io.Serializable {
    private String name;
    private Integer id;
}
