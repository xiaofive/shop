package com.shop.product.brand.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 品牌
 * Author: wang Y
 * Date: 2021-05-31
 */
@Accessors(chain = true)
@Data
public class SpBrand extends BaseEntity {

    private String name;
    private String firstLetter;
    private Integer sort;
    private Boolean isBrandManufacturer;
    private Boolean enable;
    private Integer productNum;
    private Integer productCommentNum;
    private String logo;
    private String bigPicture;
    private String brandStory;

}
