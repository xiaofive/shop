package com.shop.product.category.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 商品属性值
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductAttributeValueReq {

    /**
     * 商品id
     */
    @NotNull(message = "not_empty")
    private Long productId;
    /**
     * 商品属性id
     */
    @NotNull(message = "not_empty")
    private Long productAttributeId;
    /**
     * 属性值
     */
    @NotEmpty(message = "not_empty")
    private String value;

}
