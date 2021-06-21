package com.shop.common.util;

import com.google.common.collect.Maps;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Cookie相关操作
 * <p>
 * Author: wang Y
 * Date: 2021-06-21
 */
public class CookieUtils {

    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param name
     * @return: javax.servlet.http.Cookie
     * @Date: 2021-06-21
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return: java.util.Map<java.lang.String, javax.servlet.http.Cookie>
     * @Date: 2021-06-21
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = Maps.newHashMap();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 保存Cookies
     *
     * @param response response响应
     * @param name     cookie的名字
     * @param value    cookie的值
     * @param time     cookie的存在时间
     * @return: java.util.Map<java.lang.String, javax.servlet.http.Cookie>
     * @Date: 2021-06-21
     */
    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);
        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 单位：秒
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        return response;
    }

    /**
     * 删除无效cookie
     *
     * @param request   请求
     * @param response  响应
     * @param deleteKey 需要删除cookie的名称
     * @return: void
     * @Date: 2021-06-21
     */
    private void delectCookieByName(HttpServletRequest request, HttpServletResponse response, String deleteKey) throws NullPointerException {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        for (String key : cookieMap.keySet()) {
            if (key == deleteKey && key.equals(deleteKey)) {
                Cookie cookie = cookieMap.get(key);
                // 默认值是-1，即：关闭浏览器时就清除cookie;
                // 当设置为0的时候：创建完cookie，使用后马上就删除;
                // 因为时间到了，又因为，cookie没有清除方法，所以设置为 0，就相当于清除方法;
                // 当设置时间大于0，当时间到达后就会自动删除
                cookie.setMaxAge(0);//设置cookie有效时间为0
                cookie.setPath("/");//不设置存储路径
                response.addCookie(cookie);
            }
        }


    }
}
