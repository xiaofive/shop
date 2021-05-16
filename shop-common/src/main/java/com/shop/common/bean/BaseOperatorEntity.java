package com.shop.common.bean;

import lombok.Data;

/**
 * Created by ThomasYu on 2019-07-21
 */
@Data
public class BaseOperatorEntity extends BaseEntity {

    private String createdBy;

    private String updatedBy;
}
