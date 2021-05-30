package com.shop.product.category.bean.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性表
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductAttribute {

    private Long productAttributeCategoryId;
    private String name;
    private String selectType;
    private Integer inputType;
    private String inputList;
    private Integer sort;
    private Integer filterType;
    private Integer searchType;
    private Boolean isBind;
    private Boolean isHandAdd;
    private Integer type;

}
