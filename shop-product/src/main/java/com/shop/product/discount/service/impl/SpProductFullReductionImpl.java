package com.shop.product.discount.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.discount.bean.entity.SpProductFullReduction;
import com.shop.product.discount.mapper.SpProductFullReductionMapper;
import com.shop.product.discount.service.SpProductFullReductionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpProductFullReductionImpl extends ServiceImpl<SpProductFullReductionMapper, SpProductFullReduction> implements SpProductFullReductionService {

    @Resource
    private SpProductFullReductionMapper spProductFullReductionMapper;

}
