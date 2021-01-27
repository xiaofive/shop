package com.jyzt.common.bean;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ThomasYu on 2019-07-21
 */
@Getter
@Setter
public class BaseEntity extends BaseReadOnlyEntity {

    private Date updated;

    @Setter
    @TableLogic(value = "null", delval = "now()")
    private Date deleted;

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
