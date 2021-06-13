package com.shop.product.discount.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 商品满减折扣
 * <p>
 * Author: wang Y
 * Date: 2021-06-13
 */
@Accessors(chain = true)
@Data
public class SpProductLadder extends BaseEntity {

    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 满足折扣数量
     */
    private BigDecimal count;
    /**
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 折扣价
     */
    private BigDecimal price;

}
