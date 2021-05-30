package com.shop.product.category.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品属性值
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductAttributeValue extends BaseEntity {

    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品属性id
     */
    private Long productAttributeId;
    /**
     * 属性值
     */
    private String value;

}
