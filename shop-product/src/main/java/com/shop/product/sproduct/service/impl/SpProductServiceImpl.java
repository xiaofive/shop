package com.shop.product.sproduct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.sproduct.bean.entity.SpProduct;
import com.shop.product.sproduct.mapper.SpProductMapper;
import com.shop.product.sproduct.service.SpProductService;
import org.springframework.stereotype.Service;

@Service
public class SpProductServiceImpl extends ServiceImpl<SpProductMapper, SpProduct>implements SpProductService {
}
