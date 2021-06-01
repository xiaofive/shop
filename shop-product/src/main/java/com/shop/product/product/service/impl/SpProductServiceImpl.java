package com.shop.product.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.product.bean.entity.SpProduct;
import com.shop.product.product.mapper.SpProductMapper;
import com.shop.product.product.service.SpProductService;
import org.springframework.stereotype.Service;

@Service
public class SpProductServiceImpl extends ServiceImpl<SpProductMapper, SpProduct>implements SpProductService {
}
