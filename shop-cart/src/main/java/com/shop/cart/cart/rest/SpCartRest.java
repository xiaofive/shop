package com.shop.cart.cart.rest;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.shop.cart.cart.bean.CookieConstant;
import com.shop.cart.cart.bean.dto.SpCartCacheDTO;
import com.shop.cart.cart.bean.vo.SpCartInfo;
import com.shop.cart.cart.dao.SpCartCacheDAO;
import com.shop.cart.feign.ResdisClusterFeignClient;
import com.shop.common.util.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * 购物车服务
 * <p>
 * Author: wang Y
 * Date: 2021-06-14
 */
@Slf4j
@Api(tags = "购物车")
@RequestMapping("rest/cart")
@RestController
public class SpCartRest {

    @Resource
    private SpCartCacheDAO cartCacheDAO;


    /**
     * 加入购物车
     *
     * @param unLoginKeyByCookie 未登录
     * @param cartCacheDTO       购物车品相
     * @param response           首次未登录设置cookie
     * @return: java.lang.Integer 商品总数
     * @Date: 2021-06-20
     */
    @ApiOperation("加入购物车")
    @PostMapping("addCart")
    public Integer addCart(@CookieValue(name = CookieConstant.COOKIE_CART, required = false) String unLoginKeyByCookie,
                           @RequestBody SpCartCacheDTO cartCacheDTO,
                           HttpServletResponse response) {
        boolean isLogin = ShiroUtils.isLogin();
        String userKey = isLogin ? ShiroUtils.getCurrentUserId().toString()
                : StringUtils.isBlank(unLoginKeyByCookie)
                ? UUID.randomUUID().toString()
                : unLoginKeyByCookie;
        log.warn("userkey:".concat(userKey));
        if (!isLogin && StringUtils.isBlank(unLoginKeyByCookie)) {
            Cookie cookie = new Cookie(CookieConstant.COOKIE_CART, userKey);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(7200);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        cartCacheDAO.addCart(userKey, cartCacheDTO);
        return cartCacheDAO.countCartItemQty(userKey);

    }

    /**
     * 删除购物车中的品项
     *
     * @param unLoginKeyByCookie 未登录
     * @param productIdAndSkuIds spiltKey = productId + skuId
     * @return: void
     * @Date: 2021-06-22
     */
    @ApiOperation("删除购物车中的品相")
    @DeleteMapping("delFromCart")
    public void delFromCart(@CookieValue(name = CookieConstant.COOKIE_CART, required = false) String unLoginKeyByCookie,
                            @RequestBody List<String> productIdAndSkuIds) {
        if (CollectionUtils.isEmpty(productIdAndSkuIds)) {
            return;
        }
        boolean isLogin = ShiroUtils.isLogin();
        String userKey = isLogin ? ShiroUtils.getCurrentUserId().toString()
                : StringUtils.isBlank(unLoginKeyByCookie)
                ? UUID.randomUUID().toString()
                : unLoginKeyByCookie;
        cartCacheDAO.delFromCart(userKey, productIdAndSkuIds);
    }

    /**
     * 更新购物车 数量 or 选中状态
     *
     * @param unLoginKeyByCookie 未登录
     * @param productIdAndSkuIds splitKey = productId + skuId
     * @param qty                数量
     * @param seleted            选中状态
     * @return: void
     * @Date: 2021-06-22
     */
    @ApiOperation("更新购物车")
    @PutMapping("updateCart")
    public void updateCart(@CookieValue(name = CookieConstant.COOKIE_CART, required = false) String unLoginKeyByCookie,
                           @RequestParam("productIdAndSkuIds") String productIdAndSkuIds,
                           @RequestParam(value = "qty", required = false) Integer qty,
                           @RequestParam(value = "seleted", required = false) Integer seleted) {
        if (qty == null && seleted == null) {
            return;
        }
        boolean isLogin = ShiroUtils.isLogin();
        String userKey = isLogin ? ShiroUtils.getCurrentUserId().toString()
                : StringUtils.isBlank(unLoginKeyByCookie)
                ? UUID.randomUUID().toString()
                : unLoginKeyByCookie;
        cartCacheDAO.updateCart(userKey, productIdAndSkuIds, qty, seleted);
    }

    /**
     * 勾选所有商品和全部取消勾选
     *
     * @param unLoginKeyByCookie splitKey = productId + skuId
     * @param seleted            选中状态
     * @return: void
     * @Date: 2021-06-22
     */
    @ApiOperation("勾选所有商品和全部取消勾选")
    @PutMapping("updateSeleted")
    public void updateSeleted(@CookieValue(name = CookieConstant.COOKIE_CART, required = false) String unLoginKeyByCookie,
                              @RequestParam(value = "seleted", required = false) Integer seleted) {
        if (seleted == null) {
            return;
        }
        boolean isLogin = ShiroUtils.isLogin();
        String userKey = isLogin ? ShiroUtils.getCurrentUserId().toString()
                : StringUtils.isBlank(unLoginKeyByCookie)
                ? UUID.randomUUID().toString()
                : unLoginKeyByCookie;
        cartCacheDAO.updateSeleted(userKey, seleted);
    }

    /**
     * 获取购物车
     *
     * @param unLoginKeyByCookie
     * @return: com.shop.cart.cart.bean.vo.SpCartVO
     * @Date: 2021-06-22
     */
    @ApiOperation("获取购物车")
    @GetMapping("getCart")
    public SpCartInfo getCart(@CookieValue(name = CookieConstant.COOKIE_CART, required = false) String unLoginKeyByCookie) {
        boolean isLogin = ShiroUtils.isLogin();
        String userKey = isLogin ? ShiroUtils.getCurrentUserId().toString()
                : StringUtils.isBlank(unLoginKeyByCookie)
                ? UUID.randomUUID().toString()
                : unLoginKeyByCookie;
        return cartCacheDAO.getCart(userKey);
    }


    //1.加入购物车 done
    //2.更新购物车 done
    //3.删除购物车中的商品 done
    //4.合并临时购物车和用户购物车 done
    //5.勾选所有商品和全部取消勾选 done
    //6.查询购物车商品 done
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
