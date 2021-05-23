package com.shop.product.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class User extends BaseEntity {

    private String name;
    private Integer age;
    private String email;

}
