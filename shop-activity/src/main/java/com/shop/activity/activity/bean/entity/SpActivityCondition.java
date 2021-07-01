package com.shop.activity.activity.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 活动条件bean
 * Author: wang Y
 * Date: 2021-06-25
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class SpActivityCondition extends BaseEntity {

    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 包含分类
     */
    private String includeCategory;
    /**
     * 包含品牌
     */
    private String includeBrand;
    /**
     * 包含厂商
     */
    private String includeCompany;
    /**
     * 包含商品
     */
    private String includeGoods;
    /**
     * 排除分类
     */
    private String excludeCategory;
    /**
     * 排除品牌
     */
    private String excludeBrand;
    /**
     * 排除商品
     */
    private String excludeGoods;

}
