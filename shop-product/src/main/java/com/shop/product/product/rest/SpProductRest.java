package com.shop.product.product.rest;

import com.shop.product.product.bean.req.SpProductAddReq;
import com.shop.product.product.service.SpProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品后台管理rest
 * <p>
 * Author: wang Y
 * Date: 2021-06-01
 */
@RequestMapping("/rest/product")
@RestController
public class SpProductRest {

    @Resource
    private SpProductService spProductService;

    /**
     * 新增商品
     *
     * @param spProductAddReq
     * @return: void
     * @Date: 2021-06-01
     */
    @ApiOperation("新增商品")
    @PostMapping(value = "/create")
    public void create(@RequestBody SpProductAddReq spProductAddReq) {

    }

}
