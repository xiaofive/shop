package com.shop.product.discount.rest;

import com.shop.product.discount.bean.entity.SpProductLadder;
import com.shop.product.discount.service.SpProductLadderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品满减折扣服务
 *
 * Author: wang Y
 * Date: 2021-06-13
 */

@RequestMapping("/rest/ladder")
@RestController
public class SpProductLadderRest {

    @Resource
    private SpProductLadderService spProductLadderService;

}