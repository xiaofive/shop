package com.shop.cart.cart.util;

import com.shop.cart.cart.bean.CartConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CartUtil {

    /**
     * 获取名为"cookie_cart"的cookie
     *
     * @param request
     * @return: Cookie
     * @Date: 2021-06-20
     */
    public static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cartCookie = null;
        for (Cookie cookie : cookies) {
            if (CartConstant.COOKIE_CART.equals(cookie.getName())) {
                cartCookie = cookie;
                break;
            }
        }
        return cartCookie;
    }

}
