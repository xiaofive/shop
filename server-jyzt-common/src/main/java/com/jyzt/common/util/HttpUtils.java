package com.jyzt.common.util;

import com.jyzt.common.constant.SymbolConstant;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ThomasYu on 2019-07-31
 */
public class HttpUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static boolean isStaticResource() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return false;
        }
        return isStaticResource(request.getServletPath());
    }

    public static boolean isStaticResource(String uri) {
        ResourceUrlProvider resourceUrlProvider = SpringContextUtils.getBean(ResourceUrlProvider.class);
        String staticUri = resourceUrlProvider.getForLookupPath(uri);
        return staticUri != null;
    }

    public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip != null && ip.contains(",")) {
            // 拿第一个 ip 地址
            ip = ip.split(SymbolConstant.COMMA)[0];
        }
        if (checkIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (checkIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (checkIp(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;

    }

    private static boolean checkIp(String ip) {
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }


    /**
     * 拼接URL请求参数
     *
     * @param url
     * @param map
     * @return
     */
    public static String getAppendUrl(String url, Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (StringUtils.isEmpty(buffer.toString())) {
                    buffer.append("?");
                } else {
                    buffer.append("&");
                }
                buffer.append(entry.getKey()).append("=").append(entry.getValue());
            }
            url += buffer.toString();
        }
        return url;
    }
}
