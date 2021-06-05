package com.shop.product.category.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品属性前端bean
 * <p>
 * Author: wang Y
 * Date: 2021-05-31
 */
@Accessors(chain = true)
@Data
public class SpProductAttributeVO {

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
