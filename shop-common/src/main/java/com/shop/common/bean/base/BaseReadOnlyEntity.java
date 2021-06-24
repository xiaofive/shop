package com.shop.common.bean.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * read bean
 * <p>
 * Author: wang Y
 * Date: 2021-05-20
 */
@Data
public class BaseReadOnlyEntity implements Serializable {

    private static final long serialVersionUID = 7050795514815769409L;

    ////指定主键生成策略使用雪花算法（默认策略）
    @TableId(type = IdType.ASSIGN_ID) //TODO 待ID方案完善
    //@TableId 雪花算法（默认策略）
//    //指定主键使用数据库ID自增策略
//    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * db自动填充
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

}
