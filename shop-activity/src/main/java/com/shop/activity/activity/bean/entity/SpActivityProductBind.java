package com.shop.activity.activity.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 活动商品绑定关系
 * <p>
 * Author: wang Y
 * Date: 2021-06-25
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class SpActivityProductBind extends BaseEntity {

    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 商品id
     */
    private Long productId;

}
