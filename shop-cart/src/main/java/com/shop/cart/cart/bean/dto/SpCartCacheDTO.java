package com.shop.cart.cart.bean.dto;

import com.shop.cart.cart.bean.CartConstant;

import java.math.BigDecimal;

/**
 * 购物车缓存-Hash存储
 * <p>
 * Author: wang Y
 * Date: 2021-06-14
 */
public class SpCartCacheDTO {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 商品ID ---> 关联商品缓存
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品规格ID 同一规格商品只缓存一份
     */
    private String specId;
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


    /**
     * 购物车-Redis-Key
     *
     * @return: java.lang.String
     * @Date: 2021-06-14
     */
    public String getSplitKey() {
        return CartConstant.CART_CACHE
                + this.userId
                + this.productId
                + this.specId;
    }

}
