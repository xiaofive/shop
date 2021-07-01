package com.shop.activity.activity.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 活动规则bean
 * <p>
 * Author: wang Y
 * Date: 2021-06-25
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class SpActivityRule extends BaseEntity {

    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 优惠类型 1 满减 2 满折 3 赠品 4 满N件送M件 5 第N件折扣
     */
    private Integer reduceType;
    /**
     * 0 封顶 1 上不封顶
     */
    private String topType;
    /**
     * 0 无特殊条件 1  任选 2 同商品
     */
    private String pieceType;
    /**
     * 满额优惠活动中满减需要的达到的金额
     */
    private BigDecimal reduceAmout;
    /**
     * 达到满额后需要减的金额
     */
    private BigDecimal reduceMoney;
    /**
     * 满件优惠活动中需要达到的商品件数
     */
    private Integer pieceNumber;
    /**
     * 满几件送几件规则中，送几件的件数
     */
    private Integer activityGiveNumber;
    /**
     * 达到满件后需要优惠的折扣值
     */
    private BigDecimal discountValue;
    /**
     * 被选择的赠品的集合标识
     */
    private Integer activityGiftNumbers;


}
