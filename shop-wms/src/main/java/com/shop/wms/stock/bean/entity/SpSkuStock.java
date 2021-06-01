package com.shop.wms.stock.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 库存 SKU
 *
 * Author: wang Y
 * Date: 2021-06-01
 */
@Accessors(chain = true)
@Data
public class SpSkuStock extends BaseEntity {

    private Long productId;
    private String code;
    private BigDecimal price;
    private BigDecimal stock;
    private BigDecimal lockStock;
    private BigDecimal warnStock;
    private String specOne;
    private String specTwo;
    private String specThree;
    private String picture;
    private BigDecimal saleNum;
    private BigDecimal promotionPrice;

}
