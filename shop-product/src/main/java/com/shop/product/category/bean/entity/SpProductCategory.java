package com.shop.product.category.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shop.common.bean.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
/**
 * 类目设计
 * Author: wang Y
 * Date: 2021-05-30
 */
@Data
public class SpProductCategory extends BaseEntity {

    private Long parentId;
    private String name;
    private Integer level;
    private Integer productNum;
    private String productUnit;
    private Boolean isNav;
    private Integer enable;
    private Integer sort;
    private String icon;
    private String keywords;
    @TableField(value = "`desc`")
    private String desc;

}
