package com.shop.activity.activity.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 活动主表
 * <p>
 * Author: wang Y
 * Date: 2021-06-24
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class SpActivity extends BaseEntity {

    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动描述
     */
    private String activityDesc;
    /**
     * 促销档期编号
     */
    private String scheduleNo;
    /**
     * 档期名称字符串，以逗号区隔
     */
    private String scheduleName;
    /**
     * 活动开始时间
     */
    private Date startTime;
    /**
     * 活动结束时间
     */
    private Date endTime;
    /**
     * 多租户
     */
    private Long tenantId;
    /**
     * 活动状态: 1草稿 2 有效 3 无效
     */
    private Integer status;
    /**
     * 活动商品推送状态：1成功 2.失败--->待定
     */
    private Integer pushStatus;

}
