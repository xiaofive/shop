package com.shop.product.category.bean.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shop.product.category.bean.entity.SpProductCategory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 递归当前分类及其所有子分类
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductCategoryAllTreeVO extends SpProductCategory {

    private List<SpProductCategoryAllTreeVO> childNode;

}
