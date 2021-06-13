package com.shop.product.discount.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 会员折扣
 * <p>
 * Author: wang Y
 * Date: 2021-06-13
 */
@Accessors(chain = true)
@Data
public class SpMemberPriceReq {

    /**
     * 商品Id
     */
    @NotNull(message = "not_empty")
    private Long productId;
    /**
     * 会员等级Id,关联到会员中心模块
     */
    @NotNull(message = "not_empty")
    private Long memberLevelId;
    /**
     * 会员折扣
     */
    @NotNull(message = "not_empty")
    private BigDecimal memberDiscount;
    /**
     * 会员等级名称
     */
    @NotNull(message = "not_empty")
    private String memberLevelName;

}
