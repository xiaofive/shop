package com.shop.product.discount.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 会员折扣
 *
 * Author: wang Y
 * Date: 2021-06-13
 */
@Accessors(chain = true)
@Data
public class SpMemberPrice extends BaseEntity {

    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 会员等级Id,关联到会员中心模块
     */
    private BigDecimal memberLevelId;
    /**
     * 会员折扣
     */
    private BigDecimal memberDiscount;
    /**
     * 会员等级名称
     */
    private String memberLevelName;

}
