package com.shop.common.standard;

/**
 * 前端VO返回规范 - 选择框
 * Created by hujinhao on 2019/8/15.
 */
public interface FSelectorVORule {

    Object getFkey();

    Object getFvalue();

    Object getFtext();

    default Boolean getFdefault() {
        return Boolean.FALSE;
    }

}
