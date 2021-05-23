package com.shop.common.config.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * 全局通用自定义SQL注入器,按需添加
 *
 * Author: wang Y
 * Date: 2021-05-22
 */
public class ShopLogicSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //只需增加方法，保留MP自带方法
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectByRefId());
        methodList.add(new ListAllAvailable());
        return methodList;
    }
}
