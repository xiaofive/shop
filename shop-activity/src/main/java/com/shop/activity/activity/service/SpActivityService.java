package com.shop.activity.activity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.activity.activity.bean.entity.SpActivity;
import com.shop.activity.activity.bean.req.SpActivityReq;

public interface SpActivityService extends IService<SpActivity> {

    void create(SpActivityReq spActivityReq);

}
