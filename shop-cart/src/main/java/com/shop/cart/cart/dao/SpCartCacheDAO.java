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
    void addCart(SpCartCacheDTO spCartCacheDTO);

}
