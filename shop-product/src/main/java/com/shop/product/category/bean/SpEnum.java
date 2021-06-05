package com.shop.product.category.bean;

import lombok.Data;

public enum SpEnum {

    ATTRIBUTE_SPEC(0, "规格"),
    ATTRIBUTE_PARAM(1, "参数"),



    ;

    private Integer code;

    private String msg;

    SpEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
