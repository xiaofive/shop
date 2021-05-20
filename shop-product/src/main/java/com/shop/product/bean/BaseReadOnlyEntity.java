package com.shop.product.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Java Bean规范顶级类
 *
 * Author: wang Y
 * Date: 2021-05-20
 */
@Data
public class BaseReadOnlyEntity {

    private static final long serialVersionUID = 7050795514815769409L;

    @TableId
    private Long id;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

}
