package com.shop.product.discount.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 商品满减金额
 *
 * Author: wang Y
 * Date: 2021-06-13
 */
@Data
@Accessors(chain = true)
public class SpProductFullReduction extends BaseEntity {

    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 满足金额
     */
    private BigDecimal fullPrice;
    /**
     * 减少金额
     */
    private BigDecimal reducePrice;

}
