package com.shop.product.category.bean;

/**
 * Author: wang Y
 * Date: 2021-06-14
 */
public enum SpEnum {

    /**
     * 属性分类
     */
    ATTRIBUTE_SPEC(0, "规格"),
    /**
     * 属性分类
     */
    ATTRIBUTE_PARAM(1, "参数"),


    ;

    private final Integer code;

    private final String msg;

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
