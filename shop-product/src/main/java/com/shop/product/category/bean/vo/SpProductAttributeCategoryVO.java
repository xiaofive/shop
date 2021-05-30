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

    private String name;
    private String attributeCount;
    private Integer param_count;

}
