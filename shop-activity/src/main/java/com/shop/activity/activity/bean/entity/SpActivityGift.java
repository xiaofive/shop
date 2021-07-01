package com.shop.activity.activity.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 活动赠品bean
 * <p>
 * Author: wang Y
 * Date: 2021-06-25
 */
@Accessors(chain = true)
@Data
public class SpActivityGift extends BaseEntity {

    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 规则表主键ID
     */
    private Long ruleId;
    /**
     * 商品id
     */
    private Long productId;

}
