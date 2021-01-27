package com.wms.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author wangyang
 * @description
 * @Date 2019/11/13
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class RestApiResp extends RestResp{

    /**
     * 正确返回
     */
    @ApiModelProperty(value = "正确返回")
    private Boolean success;

    public RestApiResp(String code, String message, String traceId, Boolean success){
        super(code, message, traceId);
        this.success = success;
    }
}
