package com.shop.common.util;


import com.shop.common.exception.SysException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by hujinhao on 2019/7/30.
 */
public class NcUtils {

    /**
     * empty || ~ -> true
     *
     * @param str
     * @return
     */
    public static boolean isStrEmpty(String str) {
        return StringUtils.isEmpty(str) || "~".equals(str);
    }

    public static boolean isStrNotEmpty(String str) {
        return !isStrEmpty(str);
    }

    public static BigDecimal strToBigDecimal(String str) {
        String[] strArray = str.split("/");
        if (strArray.length != 2) {
            throw new SysException("error measrate");
        }
        return new BigDecimal(strArray[1]).divide(new BigDecimal(strArray[0]), 2, RoundingMode.HALF_UP);
    }
}
