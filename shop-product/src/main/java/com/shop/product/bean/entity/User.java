package com.shop.product.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity {

    private Long id;
    private String name;
    private Integer age;
    private String email;

}
