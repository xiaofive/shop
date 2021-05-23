package com.shop.common.bean.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

/**
 * Java Bean规范顶级类
 * <p>
 * Author: wang Y
 * Date: 2021-05-21
 */
@Data
public class BaseEntity extends BaseAutoFillEntity {

    /**
     * db自动填充
     */
    private Date updated;

    /**
     * 全局软删除 @TableLogic
     * <p>
     * Author: wang Y
     * Date: 2021-05-22
     */
    @Setter
    @TableLogic(value = "null", delval = "now()")
    private Date deleted;

    /**
     * 乐观锁
     * <p>
     * Author: wang Y
     * Date: 2021-05-23
     */
    @Version
    private Integer version;

    // orika 转换去除无用数据
    public void clearUseless() {
        this.setId(null);
        this.setCreated(null);
        this.setUpdated(null);
        this.setDeleted(null);
        this.setVersion(null);
    }

}
