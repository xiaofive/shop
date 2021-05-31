package com.shop.product.brand.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 品牌前端VO
 * Author: wang Y
 * Date: 2021-05-31
 */
@Accessors(chain = true)
@Data
public class SpBrandVO {

    private String name;
    private String firstLetter;
    private Integer sort;
    private String isBrandManufacturer;
    private Boolean enable;
    private Integer productNum;
    private Integer productCommentNum;
    private String logo;
    private String bigPicture;
    private String brandStory;

}
