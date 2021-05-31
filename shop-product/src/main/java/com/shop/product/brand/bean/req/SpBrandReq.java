package com.shop.product.brand.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 品牌请求参数
 * Author: wang Y
 * Date: 2021-05-31
 */
@Accessors(chain = true)
@Data
public class SpBrandReq {

    private Long id;//用于更新

    @NotEmpty(message = "not_empty")
    private String name;

    @NotEmpty(message = "not_empty")
    private String firstLetter;

    @NotNull(message = "not_empty")
    private Integer sort;

    @NotEmpty(message = "not_empty")
    private String isBrandManufacturer;

    @NotNull(message = "not_empty")
    private Boolean enable;

    @NotNull(message = "not_empty")
    private Integer productNum;

    @NotNull(message = "not_empty")
    private Integer productCommentNum;

    @NotEmpty(message = "not_empty")
    private String logo;

    @NotEmpty(message = "not_empty")
    private String bigPicture;

    @NotEmpty(message = "not_empty")
    private String brandStory;

}
