package com.shop.cart.cart.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 购物车
 * <p>
 * Author: wang Y
 * Date: 2021-06-14
 */
@Accessors(chain = true)
@Data
public class SpCartVO {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户ID
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String imgUrl;
    /**
     * 商品数量
     */
    private BigDecimal qty;
    /**
     * 加入价
     */
    private BigDecimal addPrice;

}
