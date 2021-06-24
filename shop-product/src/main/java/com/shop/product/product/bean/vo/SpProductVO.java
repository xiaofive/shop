package com.shop.product.product.bean.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品VO
 * Author: wang Y
 * Date: 2021-06-24
 */
@Data
public class SpProductVO {

    private Long id;//用于更新

    private Long brandId;
    private Long productCategoryId;
    private Long fareTemplateId;
    private Long productAttributeCategoryId;
    private String name;
    private String picture;
    private String itemNo;
    private Boolean isSelf;
    private Boolean isNew;
    private Boolean isRecommand;
    private Boolean isPreview;
    private Integer approveStatus;
    private Integer sort;
    private BigDecimal saleNum;
    private BigDecimal price;
    private BigDecimal promotionPrice;
    private Integer giftGrowthNum;
    private Integer giftNum;
    private Integer limitIntegralNum;
    private String subTitle;
    @TableField(value = "`desc`")
    private String desc;
    private BigDecimal tagPrice;
    private BigDecimal stock;
    private BigDecimal warnStock;
    private String unit;//TODO 待拆分出单位表  '单位',
    private BigDecimal weight;
    private String serviceIds;
    private String keywords;
    private String commit;
    private String pics;
    private String infoTitle;
    private String infoDesc;
    private String infoHtml;
    private String infoAppHtml;
    private Date promotionStartTime;
    private Date promotionEndTime;
    private Integer promotionLimitNum;
    private Integer promotionType;
    private String productCategoryName;
    private String brandName;

}
