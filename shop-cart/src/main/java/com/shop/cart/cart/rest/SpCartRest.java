package com.shop.cart.cart.rest;

import com.shop.cart.cart.bean.dto.SpCartCacheDTO;
import com.shop.cart.cart.dao.SpCartCacheDAO;
import com.shop.cart.feign.ResdisClusterFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 购物车服务
 * <p>
 * Author: wang Y
 * Date: 2021-06-14
 */
@Api(tags = "购物车")
@RequestMapping("rest/cart")
@RestController
public class SpCartRest {

    @Resource
    private SpCartCacheDAO cartCacheDAO;


    /**
     * 加入购物车
     * 返回商品总数
     * Author: wang Y
     * Date: 2021-06-17
     */
    @ApiOperation("加入购物车")
    @PostMapping
    public Integer addCart(@RequestBody SpCartCacheDTO cartCacheDTO) {
        //校验用户是否登录
        //校验商品是否存在
        //skuId/规格 是否存在
        cartCacheDAO.addCart(cartCacheDTO);
        Integer total = cartCacheDAO.countCartItemQty();
        return total;
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


    @Resource
    private ResdisClusterFeignClient rfc;

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
