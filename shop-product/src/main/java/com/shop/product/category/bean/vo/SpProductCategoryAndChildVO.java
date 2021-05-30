package com.shop.product.category.bean.vo;

import com.shop.product.category.bean.entity.SpProductCategory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 当前分类及其所有子分类Bean
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductCategoryAndChildVO extends SpProductCategory {

    private List<SpProductCategory> childProductCategorys;

}
