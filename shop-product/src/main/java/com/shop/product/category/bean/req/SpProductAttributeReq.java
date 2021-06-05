package com.shop.product.category.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 商品属性请求参数封装
 * Author: wang Y
 * Date: 2021-05-31
 */
@Accessors(chain = true)
@Data
public class SpProductAttributeReq {

    /**
     * 用于更新操作
     */
    private Long id;

    /**
     * 关联商品属性分类ID
     */
    @NotNull(message = "not_empty")
    private Long productAttributeCategoryId;

    /**
     * 属性名称
     */
    @NotEmpty(message = "not_empty")
    private String name;

    @NotEmpty(message = "not_empty")
    private String selectType;

    @NotNull(message = "not_empty")
    private Integer inputType;

    @NotEmpty(message = "not_empty")
    private String inputList;

    @NotNull(message = "not_empty")
    private Integer sort;

    @NotNull(message = "not_empty")
    private Integer filterType;

    @NotNull(message = "not_empty")
    private Integer searchType;

    /**
     * 具有相同属性Product是否关联
     */
    @NotNull(message = "not_empty")
    private Boolean isBind;

    /**
     * 是否支持手动新增
     */
    @NotNull(message = "not_empty")
    private Boolean isHandAdd;

    /**
     * 属性类型
     */
    @NotNull(message = "not_empty")
    private Integer type;

}
