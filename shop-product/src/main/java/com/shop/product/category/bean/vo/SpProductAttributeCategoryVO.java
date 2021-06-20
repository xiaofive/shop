package com.shop.product.category.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性分类前端返回bean
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductAttributeCategoryVO {

    private Long id; //用作更新

    private String name;
    private Integer attributeNum;
    private Integer paramNum;

}
