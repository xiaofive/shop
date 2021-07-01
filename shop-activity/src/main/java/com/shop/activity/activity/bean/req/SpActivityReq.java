package com.shop.activity.activity.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 创建行销活动req
 * Author: wang Y
 * Date: 2021-07-01
 */
@Accessors(chain = true)
@Data
public class SpActivityReq {

    /**
     * 活动名称
     */
    @NotEmpty(message = "not_empty")
    private String activityName;
    /**
     * 活动描述
     */
    @NotEmpty(message = "not_empty")
    private String activityDesc;
    /**
     * 促销档期编号
     */
    @NotEmpty(message = "not_empty")
    private String scheduleNo;

    /**
     * 活动开始时间
     */
    @NotNull(message = "not_empty")
    private Date startTime;
    /**
     * 活动结束时间
     */
    @NotNull(message = "not_empty")
    private Date endTime;
    /**
     * 多租户-门店
     */
    @NotNull(message = "not_empty")
    private Long tenantId;

    @Valid
    @NotNull(groups = SpActivityRuleReqValid.class)
    private SpActivityRuleReq spActivityRuleReq;

    @Valid
    @NotNull(groups = SpActivityConditionReqValid.class)
    private SpActivityConditionReq spActivityConditionReq;

    /**
     * 流转组校验
     **/
    public interface SpActivityRuleReqValid {
    }

    public interface SpActivityConditionReqValid {
    }

    /**
     * 活动规则
     * Author: wang Y
     * Date: 2021-07-01
     */
    @Accessors(chain = true)
    @Data
    private static class SpActivityRuleReq {

        /**
         * 优惠类型 :
         * 1.满额：
         * 满额减金
         * 满额赠品
         * 2.满件：
         * 满件折扣
         * 满件赠品
         * 买几送几
         * 第N件折扣
         */
        @NotEmpty(message = "not_empty")
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

    /**
     * 活动范围
     * Author: wang Y
     * Date: 2021-07-01
     */
    @Accessors(chain = true)
    @Data
    private static class SpActivityConditionReq {
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


}
