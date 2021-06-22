package com.shop.cart.cart.dao;

import com.shop.cart.cart.bean.dto.SpCartCacheDTO;

import java.util.List;

public interface SpCartCacheDAO {

    /**
     * 加入购物车
     * 数据结构：Hash
     *
     * @param spCartCacheDTO
     * @return: void
     * @Date: 2021-06-17
     */
    void addCart(String userKey, SpCartCacheDTO spCartCacheDTO);

    /**
     * 删除购物车中的品项
     *
     * @param userKey
     * @param splitKey
     * @return: void
     * @Date: 2021-06-22
     */
    void delFromCart(String userKey, List<String> splitKey);

    /**
     * 更新购物车中 品项的数量 or 选中状态
     *
     * @param userKey  cart-key
     * @param splitKey cart-field
     * @param qty      数量
     * @param selected 选中状态
     * @return: void
     * @Date: 2021-06-22
     */
    void updateCart(String userKey, String splitKey, Integer qty, Integer selected);

    /**
     * 购物车 全选 or 全不选
     *
     * @param userKey
     * @param seleted
     * @return: void
     * @Date: 2021-06-22
     */
    void updateSeleted(String userKey, Integer seleted);

    /**
     * 合并离线购物车
     *
     * @param loginKey
     * @param unLoginKey
     * @return: void
     * @Date: 2021-06-21
     */
    void mergeToCart(String loginKey, String unLoginKey);

    /**
     * 统计当前用户购物车品相总数
     * HLEN key
     * 获取哈希表中字段的数量
     *
     * @param userKey
     * @return: java.lang.Integer
     * @Date: 2021-06-22
     */
    Integer countCartItemQty(String userKey);

}
