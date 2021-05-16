package com.shop.common.exception;

import lombok.Getter;

/**
 * 程序的最外层逻辑（一般是 rest 接口类），遇到错误和异常时，必须显式抛出 SysException 或者其子类，
 * 以便框架中定义的 exception handler 能够正确处理，避免无法解读的 500 出错返回。
 *
 * SysException 需要两个参数 code 和 message（或者是定义了 getCode 和 getMessage 的 ExceptionEnumInterface 实例）。
 * code 是全局唯一的错误编码，message 是对应的文字说明。
 *
 * 为了维护全局唯一的错误编码，code 必须定义在实现了 ExceptionEnumInterface 接口的 XXXErrorEnum 枚举类中，
 * 例如 NcErrorEnum、SysErrorEnum、ApprovalErrorEnum、BillErrorEnum 等。
 *
 * 为了支持国际化，ExceptionEnumInterface 中的 message 应该是一个占位符（例如 no_extbillservice），
 * 而真正的提示字符串需要在 resources.lang 目录中的 message_xx.properties 文件中定义。
 * 这样今后增加不同国家语言支持的时候，只需要简单增加语言资源文件即可。
 *
 * Created by ThomasYu on 2019-07-21
 */
@Getter
public class SysException extends RuntimeException {

    private String code;

    private String message;

    private Object[] args;

    public SysException(String message) {
        this.message = message;
        this.code = "-1";
        this.args = null;
    }

    public SysException(String code, String message) {
        this.message = message;
        this.code = code;
        this.args = null;
    }

    public SysException(String message, Object... args) {
        this.message = message;
        this.code = "-1";
        this.args = args;
    }

    public SysException(ExceptionEnumInterface exceptionEnumInterface, Object... args) {
        this.message = exceptionEnumInterface.getMsg();
        this.code = exceptionEnumInterface.getCode();
        this.args = args;
    }

}
