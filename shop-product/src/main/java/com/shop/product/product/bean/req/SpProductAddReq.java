package com.shop.product.product.bean.req;

import com.shop.common.dto.wms.SpSkuStockDTO;
import com.shop.product.category.bean.entity.SpProductAttributeValue;
import com.shop.product.category.bean.req.SpProductAttributeValueReq;
import com.shop.product.discount.bean.req.SpMemberPriceReq;
import com.shop.product.discount.bean.req.SpProductFullReductionReq;
import com.shop.product.discount.bean.req.SpProductLadderReq;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 新增商品req
 * <p>
 * Author: wang Y
 * Date: 2021-06-01
 */
@Accessors
@Data
public class SpProductAddReq {

    /**
     * 商品分类
     */
    private Long productCategoryId;
    /**
     * 商品属性分类
     */
    private Long productAttributeCategoryId;
    /**
     * 商品品牌
     */
    private Long brandId;
    /**
     * 运费模版
     */
    private Long fareTemplateId;
    /**
     * 货号
     */
    private String itemNo;
    /**
     * 品名
     */
    private String name;
    /**
     * 商品列表图片
     */
    private String picture;
    /**
     * 画册图片
     */
    private String pics;
    /**
     * 单位
     */
    private String unit;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 吊牌价
     */
    private BigDecimal tagPrice;
    /**
     * 备注
     */
    private String commit;
    /**
     * 服务项
     */
    private String service_ids;
    /**
     * 排序字段
     */
    private Integer sort;
    //---------详情----------
    /**
     * 详情标题
     */
    private String infoTitle;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 详情描述
     */
    private String infoDesc;
    /**
     * PC端
     */
    private String infoHtml;
    /**
     * APP端
     */
    private String infoAppHtml;
    //------------状态------------
    /**
     * 审核状态
     */
    private Integer approveStatus;
    /**
     * 是否推荐
     */
    private Boolean isRecommand;
    /**
     * 是否新品
     */
    private Boolean isNew;
    /**
     * 是否上架
     */
    private Boolean isSelf;
    /**
     * 是否为预告商品
     */
    private Boolean isPreview;
    //+------------ES-------------
    /**
     * 搜索命中关键字
     */
    private String keywords;
    /**
     * 销量
     */
    private BigDecimal saleNum;

    //----------积分------------
    /**
     * 成长值
     */
    private Integer giftGrowthNum;
    /**
     * 积分
     */
    private Integer giftNum;
    /**
     * 限制使用数量
     */
    private Integer limitIntegralNum;
    //---------促销-----------
    /**
     * startDate
     */
    private Date promotionStartTime;
    /**
     * endDate
     */
    private Date promotionEndTime;
    /**
     * 促销限购数量
     */
    private Integer promotionLimitNum;
    /**
     * 促销类型
     */
    private Integer promotionType;
    //----------库存----------
    /**
     * 库存
     */
    private BigDecimal stock;
    /**
     * 预警库存
     */
    private BigDecimal warnStock;
    //-----------关联信息---------------
    //满减金额
    private List<SpProductLadderReq> spProductLadderReqs;
    //满减折扣
    private List<SpProductFullReductionReq> spProductFullReductionReqs;
    //会员价格
    private List<SpMemberPriceReq> spMemberPriceReqs;
    //属性值
    private List<SpProductAttributeValueReq> spProductAttributeValueReqs;
    //库存
    private List<SpSkuStockDTO> stockList;

}
