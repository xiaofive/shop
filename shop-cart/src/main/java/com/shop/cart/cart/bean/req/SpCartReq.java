package com.shop.cart.cart.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 购物车
 * <p>
 * Author: wang Y
 * Date: 2021-06-14
 */
@Accessors(chain = true)
@Data
public class SpCartReq {

    /**
     * 用户ID
     */
    @NotNull(message = "not_empty")
    private Long userId;
    /**
     * 商品ID ---> 关联商品缓存
     */
    @NotNull(message = "not_empty")
    private Long productId;
    /**
     * 商品名称
     */
    @NotEmpty(message = "not_empty")
    private String productName;
    /**
     * 商品规格ID 同一规格商品只缓存一份
     */
    @NotNull(message = "not_empty")
    private String specId;
    /**
     * 商品图片
     */
    @NotEmpty(message = "not_empty")
    private String imgUrl;
    /**
     * 商品数量
     */
    @NotNull(message = "not_empty")
    private BigDecimal qty;
    /**
     * 加入价
     */
    @NotNull(message = "not_empty")
    private BigDecimal addPrice;

}
