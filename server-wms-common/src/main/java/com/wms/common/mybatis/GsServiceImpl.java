package com.wms.common.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * Created by hujinhao on 2019/8/22.
 */
public class GsServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    public GsServiceImpl(M baseMapper) {
        super.baseMapper = baseMapper;
    }

}
