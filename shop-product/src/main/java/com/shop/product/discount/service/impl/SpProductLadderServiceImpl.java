package com.shop.product.discount.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.discount.bean.entity.SpProductLadder;
import com.shop.product.discount.mapper.SpProductLadderMapper;
import com.shop.product.discount.service.SpProductLadderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpProductLadderServiceImpl extends ServiceImpl<SpProductLadderMapper, SpProductLadder> implements SpProductLadderService {

    @Resource
    private SpProductLadderMapper spProductLadderMapper;


}
