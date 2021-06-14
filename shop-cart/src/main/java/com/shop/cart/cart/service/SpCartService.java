package com.shop.cart.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.cart.cart.bean.entity.SpCart;
import com.shop.cart.cart.bean.req.SpCartReq;
import com.shop.cart.cart.bean.vo.SpCartVO;

import java.util.List;

public interface SpCartService extends IService<SpCart> {

    /**
     * 购物车-添加
     *
     * @param spCartReq
     * @return: void
     * @Date: 2021-06-14
     */
    void addUserCart(SpCartReq spCartReq);

    /**
     * 购物车-删除
     *
     * @param id
     * @Param:
     * @return: void
     * @Date: 2021-06-14
     */
    void deleteCart(Long id);

    /**
     * 购物车-修改
     *
     * @param spCartReq
     * @Param:
     * @return: void
     * @Date: 2021-06-14
     */
    void editUserCart(SpCartReq spCartReq);

    /**
     * 获取当前用户购物车列表
     *
     * @return: java.util.List
     * @Date: 2021-06-14
     */
    List<SpCartVO> getUserCartList();


}
