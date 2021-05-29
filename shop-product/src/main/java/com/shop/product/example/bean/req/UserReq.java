package com.shop.product.example.bean.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * User请求bean
 *
 * Author: wang Y
 * Date: 2021-05-21
 */
@Data
public class UserReq {

    @NotNull(message = "not_empty")
    private Long id;
    @NotEmpty(message = "not_empty")
    private String name;
    private Integer age;
    private String email;

}
