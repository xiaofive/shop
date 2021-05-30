package com.shop.product.category.bean.vo;

import lombok.Data;

/**
 * 商品分类前端展示VO
 * Author: wang Y
 * Date: 2021-05-30
 */
@Data
public class SpProductCategoryVO {

    private Long parentId;
    private String name;
    private Integer level;
    private Integer productCount;
    private String productUnit;
    private Integer navStatus;
    private Integer enable;
    private Integer sort;
    private String icon;
    private String keywords;
    private String desc;

}
