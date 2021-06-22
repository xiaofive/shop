package com.shop.cart.cart.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车详情
 * <p>
 * Author: wang Y
 * Date: 2021-06-22
 */
@Accessors(chain = true)
@Data
public class SpCartInfo {

    /**
     * 购物车商品总数
     */
    private Integer totalQty;
    /**
     * 购物车总价
     */
    private BigDecimal cartTotalPrice;
    /**
     * 选中商品总价
     */
    private BigDecimal selectProTotalPrice;
    /**
     * 行销活动优惠金额，单位元
     */
    private BigDecimal campaignDiscount;
    /**
     * 运费，目前运费都是0，显示免运费
     */
    private BigDecimal freight;
    /**
     * 整个购物车是否全选
     */
    private Integer isSelectAll;

    /**
     * 商品列表
     */
    List<ProductVO> productVOList;


}
