package com.shop.cart.cart.dao;

import com.shop.cart.cart.bean.dto.SpCartCacheDTO;

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
     * @return: java.lang.Integer
     * @Date: 2021-06-19
     */
    Integer countCartItemQty(String userId);

}
