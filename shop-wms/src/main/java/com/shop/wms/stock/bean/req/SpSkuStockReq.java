package com.shop.wms.stock.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 库存 SKU //TODO 待集成WMS模块
 * <p>
 * Author: wang Y
 * Date: 2021-06-01
 */
@Accessors(chain = true)
@Data
public class SpSkuStockReq {

    private Long id; //用于更新

    @NotNull(message = "not_empty")
    private Long productId;

    @NotEmpty(message = "not_empty")
    private String code;

    @NotNull(message = "not_empty")
    private BigDecimal price;

    @NotNull(message = "not_empty")
    private BigDecimal stock;

    @NotNull(message = "not_empty")
    private BigDecimal lockStock;

    @NotNull(message = "not_empty")
    private BigDecimal warnStock;

    @NotEmpty(message = "not_empty")
    private String specOne;

    @NotEmpty(message = "not_empty")
    private String specTwo;

    @NotEmpty(message = "not_empty")
    private String specThree;

    @NotEmpty(message = "not_empty")
    private String picture;

    @NotNull(message = "not_empty")
    private BigDecimal saleNum;

    @NotNull(message = "not_empty")
    private BigDecimal promotionPrice;

}
