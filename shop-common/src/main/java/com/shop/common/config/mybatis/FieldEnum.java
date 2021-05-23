package com.shop.common.config.mybatis;

/**
 * Mybatis-Plus 自动填充枚举
 * <p>
 * Author: wang Y
 * Date: 2021-05-23
 */
public class FieldEnum {

    public enum FieldFill {
        /**
         * 默认不处理
         */
        DEFAULT,
        /**
         * 插入填充字段
         */
        INSERT,
        /**
         * 更新填充字段
         */
        UPDATE,
        /**
         * 插入和更新填充字段
         */
        INSERT_UPDATE
    }

}
