package com.shop.product.category.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性分类
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductAttributeCategory extends BaseEntity {

    private String name;
    private Integer attributeNum;
    private Integer paramNum;

}
