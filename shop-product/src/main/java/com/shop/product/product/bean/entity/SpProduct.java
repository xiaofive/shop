package com.shop.product.product.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品 SPU
 * Author: wang Y
 * Date: 2021-06-01
 */
@Accessors(chain = true)
@Data
public class SpProduct extends BaseEntity {

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
    private Integer approveStatus;
    private Integer sort;
    private BigDecimal saleNum;
    private BigDecimal price;
    private BigDecimal promotionPrice;
    private Integer giftGrowthNum;
    private Integer giftPointNum;
    private Integer limitIntegralNum;
    private String subTitle;
    private String desc;
    private BigDecimal tagPrice;
    private BigDecimal stock;
    private BigDecimal warnStock;
    private String unit;//TODO 待拆分出单位表  '单位',
    private BigDecimal weight;
    private Boolean isPreview;
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
