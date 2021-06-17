package com.shop.cart.cart.rest;

import com.shop.cart.cart.service.SpCartService;
import com.shop.cart.feign.ResdisClusterFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 购物车服务
 * <p>
 * Author: wang Y
 * Date: 2021-06-14
 */
@RequestMapping("rest/cart")
@RestController
public class SpCartRest {

    @Resource
    private SpCartService spCartService;

    @Resource
    private ResdisClusterFeignClient rfc;


    /**
     * 加入购物车
     *
     * Author: wang Y
     * Date: 2021-06-17
     */
    @PostMapping
    public void addCart() {

    }





    //1.加入购物车
    //2.更新购物车
    //3.删除购物车中的商品
    //4.合并临时购物车和用户购物车
    //5.勾选所有商品和全部取消勾选
    //6.查询购物车商品
    //7.查询购物车金额和商品数量
    //8.行销活动聚合页查询参与活动的商品
    //9.订单结算接口
    //10.查询优惠券列表
    //11.判断商品是否支持配送
    //12.提交订单接口

    //购物车-Cache-DB 缓存一致性设计

    /**
     * test feign 调用、熔断
     * <p>
     * Author: wang Y
     * Date: 2021-06-16
     */
    @GetMapping
    public String testFallBack() {
        System.out.println("request");
        return rfc.testFeignFallBack();
    }


}
