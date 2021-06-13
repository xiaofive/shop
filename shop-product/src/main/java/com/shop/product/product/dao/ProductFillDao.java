package com.shop.product.product.dao;

import com.shop.common.bean.base.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface ProductFillDao<T extends BaseEntity> {

    void save(T obj);

}
