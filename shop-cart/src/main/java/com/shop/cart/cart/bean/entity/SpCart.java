package com.shop.cart.cart.bean.entity;

import com.shop.common.bean.base.BaseEntity;
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
public class SpCart extends BaseEntity {

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

}
