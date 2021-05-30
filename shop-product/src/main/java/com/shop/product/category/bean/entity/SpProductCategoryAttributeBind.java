package com.shop.product.category.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品分类和属性的关系
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductCategoryAttributeBind extends BaseEntity {

    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品属性id
     */
    private Long ProductAttributeId;

}
