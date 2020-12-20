package cn.valjean.common.entity;

import lombok.Data;

@Data
public class User implements java.io.Serializable {
    private Integer id;
    private String name;
}
