package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.category.bean.entity.SpProductAttributeValue;
import com.shop.product.category.mapper.SpProductAttributeValueMapper;
import com.shop.product.category.service.SpProductAttributeValueService;
import org.springframework.stereotype.Service;

@Service
public class SpProductAttributeValueServiceImpl extends ServiceImpl<SpProductAttributeValueMapper, SpProductAttributeValue> implements SpProductAttributeValueService {
}
