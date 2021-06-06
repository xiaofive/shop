package com.shop.common.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 选择框 - 前端返回规范
 * <p>
 * Author: wang Y
 * Date: 2021-06-05
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultSelectVO {

    public String refId;
    public String fkey;
    public Object fvalue;
    public String ftext;
    public Boolean fdefault = Boolean.FALSE;

    public DefaultSelectVO(String fkey, Object fvalue, String ftext, String refId) {
        this.fkey = fkey;
        this.fvalue = fvalue;
        this.ftext = ftext;
        this.refId = refId;
    }

}
