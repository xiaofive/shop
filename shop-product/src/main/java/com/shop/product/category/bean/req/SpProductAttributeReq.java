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

    @NotNull(message = "not_empty")
    private Long productAttributeCategoryId;

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

    @NotNull(message = "not_empty")
    private Boolean isBind;

    @NotNull(message = "not_empty")
    private Boolean isHandAdd;

    @NotNull(message = "not_empty")
    private Integer type;

}
