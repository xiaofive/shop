package com.shop.product.category.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性表
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductAttribute extends BaseEntity {

    private Long productAttributeCategoryId;
    private String name;
    private Integer selectType;
    private Integer inputType;
    private String inputList;
    private Integer sort;
    private Integer filterType;
    private Integer searchType;
    private Boolean isBind;
    private Boolean isHandAdd;
    private Integer type;

}
