package com.shop.common.bean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 本系统所有模块自定义Mapper文件均需继承自该Mapper
 * <p>
 * Author: wang Y
 * Date: 2021-05-22
 */
public interface ShopBaseMapper<T> extends BaseMapper<T> {

    /**
     * 根据 refId 查询,根据本系统建表规范，每张表会有refId字段，核心数据避免直接根据ID进行查询
     * <p>
     * Author: wang Y
     * Date: 2021-05-22
     */
    T selectByRefId(String refId);

    /**
     * 获取有效数据，排除已经软删除的数据
     * <p>
     * Author: wang Y
     * Date: 2021-05-22
     */
    List<T> listAllAvailable();

}
