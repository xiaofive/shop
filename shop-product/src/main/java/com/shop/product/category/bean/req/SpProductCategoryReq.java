package com.shop.product.category.bean.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 商品分类请求参数封装
 * 用于商品分类的新增及修改传入
 * Author: wang Y
 * Date: 2021-05-30
 */
@Data
public class SpProductCategoryReq {

    @NotNull(message = "not_empty")
    private Long parentId;

    @NotEmpty(message = "not_empty")
    private String name;

    @NotNull(message = "not_empty")
    private Integer level;

    @NotNull(message = "not_empty")
    private Integer productNum;

    @NotEmpty(message = "not_empty")
    private String productUnit;

    @NotNull(message = "not_empty")
    private Integer navStatus;

    @NotNull(message = "not_empty")
    private Integer enable;

    @NotNull(message = "not_empty")
    private Integer sort;

    @NotEmpty(message = "not_empty")
    private String icon;

    @NotEmpty(message = "not_empty")
    private String keywords;

    @NotEmpty(message = "not_empty")
    private String desc;

}
