package com.shop.product.category.rest;

import com.shop.product.category.service.SpProductAttributeValueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *商品属性值相关rest
 *
 * Author: wang Y
 * Date: 2021-05-31
 */
@RequestMapping("/rest/productAttributeValue")
@RestController
public class ProductAttributeValueRest {

    @Resource
    private SpProductAttributeValueService spProductAttributeValueService;



}
