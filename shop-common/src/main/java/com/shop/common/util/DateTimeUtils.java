package com.shop.common.util;

import com.shop.common.constant.SymbolConstant;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ThomasYu on 2019-07-20
 */
public abstract class DateTimeUtils {
    public static final String PT_YMDHMS = "yyyyMMddHHmmss";
    public static final String PT_YMD_HYPHEN = "yyyy-MM-dd";
    public static final String PT_YMD = "yyyyMMdd";
    public static final String PT_MD = "MM-dd";
    public static final String PT_Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String PT_YMD_CHINESE_UNIT = "yyyy年MM月dd日";
    public static final String PT_YMD_START = "yyyy-MM-dd 00:00:00";
    public static final String PT_DMY = "dd-MM-yyyy";
    public static final String PT_SLASH_DMY = "dd/MM/yyyy";
    public static final String PT_YMD_D = "yyyy-MM-dd HH";
    public static final String PT_HMS = "HH:mm:ss";
    public static final String PT_HMSS = "HH:mm:ss:SSS";

    public static final DateTimeFormatter FT_YMDHMS = DateTimeFormat.forPattern(PT_YMDHMS);
    public static final DateTimeFormatter FT_YMD_HYPHEN = DateTimeFormat.forPattern(PT_YMD_HYPHEN);
    public static final DateTimeFormatter FT_YMD = DateTimeFormat.forPattern(PT_YMD);
    public static final DateTimeFormatter FT_MD = DateTimeFormat.forPattern(PT_MD);
    public static final DateTimeFormatter FT_Y_M_D_HMS = DateTimeFormat.forPattern(PT_Y_M_D_HMS);
    public static final DateTimeFormatter FT_YMD_CHINESE_UNIT = DateTimeFormat.forPattern(PT_YMD_CHINESE_UNIT);
    public static final DateTimeFormatter FT_YMD_START = DateTimeFormat.forPattern(PT_YMD_START);
    public static final DateTimeFormatter FT_DMY = DateTimeFormat.forPattern(PT_DMY);
    public static final DateTimeFormatter FT_SLASH_DMY = DateTimeFormat.forPattern(PT_SLASH_DMY);
    public static final DateTimeFormatter FT_YMD_D = DateTimeFormat.forPattern(PT_YMD_D);
    public static final DateTimeFormatter FT_HMS = DateTimeFormat.forPattern(PT_HMS);
    public static final DateTimeFormatter FT_HMSS = DateTimeFormat.forPattern(PT_HMSS);

    public static Date parseDate(String text, DateTimeFormatter formatter) {
        DateTime dateTime = parseDateTime(text, formatter);
        if (dateTime != null) {
            return dateTime.toDate();
        }
        return null;
    }

    public static DateTime parseDateTime(String text, DateTimeFormatter formatter) {
        if (text != null && !text.isEmpty() && !SymbolConstant.NULL.equals(text)) {
            return formatter.parseDateTime(text);
        }
        return null;
    }

    public static String format(Date date, DateTimeFormatter formatter) {
        if (date != null) {
            return formatter.print(date.getTime());
        }
        return null;
    }

    public static String format(DateTimeFormatter formatter) {
        return formatter.print(System.currentTimeMillis());
    }

    public static int getBetweenDays(Date from, Date to) {
        if (from != null && to != null) {
            DateTime fromD = new DateTime(from).withTimeAtStartOfDay();
            DateTime toD = new DateTime(to).withTimeAtStartOfDay();
            return Days.daysBetween(fromD, toD).getDays();
        }
        return 0;
    }

    public static Date addMinute(Date date, int minute) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minute).toDate();
    }

    /**
     * @param date     时间
     * @param timeUnit @see Calendar.MONTH Calendar.YEAR etc...
     * @param time
     * @return
     */
    public static Date calculationTime(Date date, int timeUnit, int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(timeUnit, calendar.get(timeUnit) + time);
        return calendar.getTime();
    }


    public static Boolean compareDate(Date dt1, Date dt2) {
        if (dt1.getTime() > dt2.getTime())//比较时间大小,如果dt1大于dt2
        {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将当前时间按照指定格式字符串返回
     *
     * @author ZhangHao
     * @date 2019-09-29
     */
    public static String formatDate(String formatStr, Date date) {
        Assert.hasLength(formatStr);
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    /**
     * 获取距离当前时间的时间格式化字符串
     * @author ZhangHao
     * @date 2019-10-11
     * @param formatStr 日期的字符串格式
     * @param minutesGap 距离当前时间的分钟数
     */
    public static String getPastFormatDate(String formatStr, int minutesGap, Date nowDate) {
        Assert.hasLength(formatStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int currentHour = cal.get(Calendar.MINUTE);
        cal.set(Calendar.MINUTE, currentHour - minutesGap);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    /**
     * 将日期向后或向前推n天
     * @param date
     * @param n  正数往后推,负数往前移动
     * @return
     */
    public static Date getDiff(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); //需要将date数据转移到Calender对象中操作
        calendar.add(calendar.DATE, n); //把日期往后增加n天
        return calendar.getTime();
    }

}
