package com.shop.cart.cart.bean.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品选中状态Cache -HASH
 *                  key             field               val
 * 选中状态缓存      userId     productId + skuId       isSelect
 * Author: wang Y
 * Date: 2021-06-19
 */
@Accessors(chain = true)
@Data
public class SpSelectStatusCacheDTO {

    private Long userId;

    private Long productId;

    /**
     * 购物车商品勾选状态 勾选传1，不勾选传0
     */
    private Integer isSelect;

    /**
     * 购物车项-选中状态-field
     *
     * @return: java.lang.String
     * @Date: 2021-06-14
     */
    public String getSplitKey() {
        return this.productId.toString();
    }

}
