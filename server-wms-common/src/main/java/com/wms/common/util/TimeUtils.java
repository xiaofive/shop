package com.wms.common.util;


import lombok.extern.slf4j.Slf4j;

/**
 * 分析程序运行时间工具类
 * 
 * @author ZhangHao
 * @since 2020-03-26
 */
@Slf4j
public class TimeUtils {
    
    private static Long startTime;
    
    public static void start() {
        startTime = System.currentTimeMillis();
    }
    
    public static void end(String info) {
        Long totalTime = System.currentTimeMillis() - startTime;
        log.info(info + "-------- 耗费时间 -------->>>>>>>>>  " + (totalTime / 1000.0) + " 秒");
    }
    
}
