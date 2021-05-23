package com.shop.common.bean.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 自动填充字段
 * <p>
 * Author: wang Y
 * Date: 2021-05-23
 */
@Data
public class BaseAutoFillEntity extends BaseReadOnlyEntity {

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updatedBy;

    /**
     * 全局唯一 分布式ID
     * //TODO 待完善ID生成规则
     */
    @TableField(fill = FieldFill.INSERT)
    private String refId;

}
