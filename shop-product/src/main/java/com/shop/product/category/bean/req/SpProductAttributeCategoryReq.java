package com.shop.product.category.bean.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 属性分类前端请求bean
 * Author: wang Y
 * Date: 2021-05-30
 */
@Accessors(chain = true)
@Data
public class SpProductAttributeCategoryReq {

    private Long id;

    @NotEmpty(message = "not_empty")
    private String name;

}
