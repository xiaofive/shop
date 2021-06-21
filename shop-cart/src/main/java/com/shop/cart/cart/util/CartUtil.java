package com.shop.cart.cart.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.shop.cart.cart.bean.CookieConstant;
import com.shop.cart.cart.bean.dto.SpCartCacheDTO;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class CartUtil {

    /**
     * 获取名为"cookie_cart"的cookie
     *
     * @param request
     * @return: Cookie
     * @Date: 2021-06-20
     */
    private static Cookie getCookie(HttpServletRequest request) {
        String header = request.getHeader("cookie");
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        Cookie cartCookie = null;
        for (Cookie cookie : cookies) {
            if (CookieConstant.COOKIE_CART.equals(cookie.getName())) {
                cartCookie = cookie;
                break;
            }
        }
        return cartCookie;
    }

    /**
     * 反序列化未登录情况下Cookie中的离线购物车数据
     *
     * @param request
     * @return: java.util.List<com.shop.cart.cart.bean.dto.SpCartCacheDTO>
     * @Date: 2021-06-20
     */
    public static List<SpCartCacheDTO> getCartsByCookie(HttpServletRequest request) {
        Cookie cartCookie = getCookie(request);
        //FIXME 待删
        cartCookie.setValue(buildCookieCarts());
        if (cartCookie == null) {
            return Collections.emptyList();
        }
        String unLoginCartStr = cartCookie.getValue();
        if (StringUtils.isBlank(unLoginCartStr)) {
            return Collections.emptyList();
        }
        List<SpCartCacheDTO> unLoginCarts = JSONObject.parseArray(unLoginCartStr, SpCartCacheDTO.class);
        return unLoginCarts;
    }

    /**
     * 模拟向Cookie添加离线购物车数据
     *
     * @return: void
     * @Date: 2021-06-20
     */
    private static String buildCookieCarts() {

        SpCartCacheDTO spCartCacheDTO = new SpCartCacheDTO();
        spCartCacheDTO.setAddPrice(new BigDecimal(23.56))
                .setQty(1)
                .setProductId(33L)
                .setSkuId("33");

        SpCartCacheDTO spCartCacheDTOTwo = new SpCartCacheDTO();
        spCartCacheDTOTwo.setAddPrice(new BigDecimal(23.56))
                .setQty(1)
                .setProductId(55L)
                .setSkuId("55");

        List<SpCartCacheDTO> carts = Lists.newLinkedList();
        carts.add(spCartCacheDTO);
        carts.add(spCartCacheDTOTwo);

        //log.info(JSON.toJSONString(carts));
        return JSON.toJSONString(carts);

    }

}
