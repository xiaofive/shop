package com.shop.activity.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.activity.activity.bean.entity.SpActivity;
import com.shop.activity.activity.mapper.SpActivityMapper;
import com.shop.activity.activity.service.SpActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpActivityServiceImpl extends ServiceImpl<SpActivityMapper, SpActivity> implements SpActivityService {

    @Resource
    private SpActivityMapper spActivityMapper;

}
