package com.shop.wms.stock.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * SKU前端展示
 * Author: wang Y
 * Date: 2021-06-01
 */
@Accessors(chain = true)
@Data
public class SpSkuStockVO {

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
