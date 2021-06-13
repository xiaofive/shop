package com.shop.product.discount.bean.req;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品满减折扣
 * <p>
 * Author: wang Y
 * Date: 2021-06-13
 */
@Accessors(chain = true)
@Data
public class SpProductLadderReq extends BaseEntity {

    /**
     * 商品ID
     */
    @NotNull(message = "not_empty")
    private Long productId;
    /**
     * 满足折扣数量
     */
    @NotNull(message = "not_empty")
    private BigDecimal count;
    /**
     * 折扣
     */
    @NotNull(message = "not_empty")
    private BigDecimal discount;
    /**
     * 折扣价
     */
    @NotNull(message = "not_empty")
    private BigDecimal price;

}
