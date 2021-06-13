package com.shop.product.discount.rest;

import com.shop.product.discount.service.SpMemberPriceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 会员折扣服务
 *
 * Author: wang Y
 * Date: 2021-06-13
 */
@RequestMapping("/rest/member/price")
@RestController
public class SpMemberPriceRest {

    @Resource
    private SpMemberPriceService spMemberPriceService;

}
