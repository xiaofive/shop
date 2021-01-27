package com.jyzt.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 接口返回数据格式
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class RestResp implements Serializable {

    private static final long serialVersionUID = -7775008043655916778L;

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private String code;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";

    /**
     * 返回追踪id
     */
    @ApiModelProperty(value = "请求追踪Id")
    private String traceId;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public RestResp(String code, String message, String traceId) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
    }

    public RestResp(String message, String traceId) {
        this.message = message;
        this.traceId = traceId;
    }

    public RestResp(String message) {
        this.message = message;
    }
}