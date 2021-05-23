package com.shop.common.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 顶级 字段填充
 * 作用范围：shop所有模块
 * <p>
 * Author: wang Y
 * Date: 2021-05-23
 */
@Slf4j
@Component
public class ShopMetaObjectHandler implements MetaObjectHandler {

    /**
     * insert 字段自动填充
     * 作用字段：
     * createdBy created_by
     * refId ref_id
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createdBy", String.class, "system_create"); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "refId", String.class, UUID.randomUUID().toString()); // 起始版本 3.3.0(推荐使用)
    }

    /**
     * update 字段自动填充
     * 作用字段：
     * updatedBy updated_by
     *
     * Author: wang Y
     * Date: 2021-05-23
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updatedBy", String.class, "system_update"); // 起始版本 3.3.0(推荐)
    }
}
