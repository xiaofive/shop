package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.category.bean.entity.SpProductAttribute;
import com.shop.product.category.mapper.SpProductAttributeMapper;
import com.shop.product.category.service.SpProductAttributeService;
import org.springframework.stereotype.Service;

@Service
public class SpProductAttributeServiceImpl extends ServiceImpl<SpProductAttributeMapper, SpProductAttribute> implements SpProductAttributeService {
}
