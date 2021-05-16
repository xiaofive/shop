package com.shop.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.common.exception.ExceptionEnumInterface;
import com.shop.common.exception.SysException;
import com.shop.common.exception.SystemErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

@ApiModel(description = "rest请求的返回模型，所有模块rest正常都返回该类的对象")
@Getter
public class Result<T> {

    public static final String SUCCESSFUL_CODE = "0";
    public static final String SUCCESSFUL_MESG = "处理成功";

    @ApiModelProperty(value = "处理结果code", required = true)
    private String code;
    @ApiModelProperty(value = "处理结果描述信息")
    private String msg;
    @ApiModelProperty(value = "请求结果生成时间戳")
    private Instant time;
    @ApiModelProperty(value = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     */
    public Result(ExceptionEnumInterface errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param exceptionEnumInterface
     * @param data
     */
    public Result(ExceptionEnumInterface exceptionEnumInterface, T data) {
        this(exceptionEnumInterface);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param msg
     * @param data
     */
    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail() {
        return new Result(SystemErrorEnum.SYSTEM_ERROR);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param sysException
     * @return Result
     */
    public static Result fail(SysException sysException) {
        return fail(sysException, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(SysException sysException, Object data) {
        return new Result(sysException.getCode(), sysException.getMessage(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param exceptionEnumInterface
     * @param data
     * @return Result
     */
    public static Result fail(ExceptionEnumInterface exceptionEnumInterface, Object data) {
        return new Result<>(exceptionEnumInterface, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param exceptionEnumInterface
     * @return Result
     */
    public static Result fail(ExceptionEnumInterface exceptionEnumInterface) {
        return Result.fail(exceptionEnumInterface, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return new Result<>(SystemErrorEnum.SYSTEM_ERROR, data);
    }

    /**
     * 成功code=0
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

}
