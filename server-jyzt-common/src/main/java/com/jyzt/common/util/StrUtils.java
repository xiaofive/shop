package com.jyzt.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hujinhao on 2019/8/12.
 */
public class StrUtils {

    public final static DecimalFormat df_00 = new DecimalFormat("0.00%");

    public static boolean hasChinese(String str) {
        if (str == null) {
            return false;
        }
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static String decimal2percent(BigDecimal bd) {
        return decimal2percent(bd, df_00);
    }

    public static String decimal2percent(BigDecimal bd, DecimalFormat df) {
        return bd == null ? null : df.format(bd);
    }

}
