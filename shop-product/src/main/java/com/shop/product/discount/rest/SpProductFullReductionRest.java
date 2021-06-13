package com.shop.product.discount.rest;

import com.shop.product.discount.bean.entity.SpProductFullReduction;
import com.shop.product.discount.service.SpProductFullReductionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品满减金额服务
 *
 * Author: wang Y
 * Date: 2021-06-13
 */
@RequestMapping("/rest/full/reduce")
@RestController
public class SpProductFullReductionRest {

    @Resource
    private SpProductFullReductionService spProductFullReductionService;

}
