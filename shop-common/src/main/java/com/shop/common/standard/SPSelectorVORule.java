package com.shop.common.standard;

/**
 * 前端VO返回规范 - 选择框
 * 本系统所有下拉框均遵循此规范
 * <p>
 * Author: wang Y
 * Date: 2021-06-05
 */
public interface SPSelectorVORule {

    Object getFkey();

    Object getFvalue();

    Object getFtext();

    Object getRefId();

    default Boolean getFdefault() {
        return Boolean.FALSE;
    }

    default Integer getPageSize() {
        return 1;
    }

    default Integer getPageNum() {
        return 15;
    }

}
