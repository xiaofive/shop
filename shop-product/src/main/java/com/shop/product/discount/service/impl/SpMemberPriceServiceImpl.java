package com.shop.product.discount.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.discount.bean.entity.SpMemberPrice;
import com.shop.product.discount.mapper.SpMemberPriceMapper;
import com.shop.product.discount.service.SpMemberPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpMemberPriceServiceImpl extends ServiceImpl<SpMemberPriceMapper, SpMemberPrice> implements SpMemberPriceService {

    @Resource
    private SpMemberPriceMapper spMemberPriceMapper;

}
