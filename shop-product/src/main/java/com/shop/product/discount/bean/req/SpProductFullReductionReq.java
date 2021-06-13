package com.shop.product.discount.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品满减金额
 * <p>
 * Author: wang Y
 * Date: 2021-06-13
 */
@Data
@Accessors(chain = true)
public class SpProductFullReductionReq {

    /**
     * 商品Id
     */
    @NotNull(message = "not_empty")
    private Long productId;
    /**
     * 满足金额
     */
    @NotNull(message = "not_empty")
    private BigDecimal fullPrice;
    /**
     * 减少金额
     */
    @NotNull(message = "not_empty")
    private BigDecimal reducePrice;

}
