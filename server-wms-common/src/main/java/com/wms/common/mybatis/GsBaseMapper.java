package com.wms.common.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * Created by ThomasYu on 2019-07-22
 */
public interface GsBaseMapper<T> extends BaseMapper<T> {

    /**
     * 根据 refId 查询
     */
    T selectByRefId(String refId);

    List<T> listAllAvailable();
}
