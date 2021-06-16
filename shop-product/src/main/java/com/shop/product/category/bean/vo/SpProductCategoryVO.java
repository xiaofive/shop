package com.shop.product.category.bean.vo;

import com.shop.common.standard.SPSelectorVORule;
import lombok.Data;

/**
 * 商品分类前端展示VO
 * Author: wang Y
 * Date: 2021-05-30
 */
@Data
public class SpProductCategoryVO implements SPSelectorVORule {

    private Long id; //仅作更新

    private String refId;
    private Long parentId;
    private String name;
    private Integer level;
    private Integer productNum;
    private String productUnit;
    private Boolean isNav;
    private Boolean enable;
    private Integer sort;
    private String icon;
    private String keywords;
    private String desc;

    @Override
    public Object getFkey() {
        return name;
    }

    @Override
    public Object getFvalue() {
        return refId;
    }

    @Override
    public Object getFtext() {
        return null;
    }

    @Override
    public Object getRefId() {
        return null;
    }
}
