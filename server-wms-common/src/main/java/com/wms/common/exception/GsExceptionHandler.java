package com.wms.common.exception;

import com.wms.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * 各模块全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GsExceptionHandler {

    private final String default_error_code = "-2";
    private final String default_error_msg = "服务内部错误，请联系管理员";

    @Resource
    protected MessageSource i18nMessageSource;

    /**
     * 处理未尽事宜
     *
     * @param ex
     * @param locale
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleExceptions(Exception ex, Locale locale,
                                   HttpServletRequest request, HttpServletResponse response) {
        log.error("", ex);
        Result result = new Result();
        try {
            Field code = result.getClass().getDeclaredField("code");
            Field msg = result.getClass().getDeclaredField("msg");
            Field time = result.getClass().getDeclaredField("time");
            Field data = result.getClass().getDeclaredField("data");
            code.setAccessible(true);
            msg.setAccessible(true);
            time.setAccessible(true);
            data.setAccessible(true);
            code.set(result, default_error_code);
            msg.set(result, default_error_msg);
            time.set(result, ZonedDateTime.now().toInstant());
            data.set(result, null);
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }

    @ExceptionHandler(SysException.class)
    public Result handleExceptions(SysException ex, Locale locale,
                                   HttpServletRequest request, HttpServletResponse response) {
        log.error("", ex);
        return Result.fail(ex);
    }

}
