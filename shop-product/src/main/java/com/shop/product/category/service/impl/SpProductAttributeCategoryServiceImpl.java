package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.category.bean.entity.SpProductAttributeCategory;
import com.shop.product.category.mapper.SpProductAttributeCategoryMapper;
import com.shop.product.category.service.SpProductAttributeCategoryService;
import org.springframework.stereotype.Service;

@Service
public class SpProductAttributeCategoryServiceImpl extends ServiceImpl<SpProductAttributeCategoryMapper, SpProductAttributeCategory> implements SpProductAttributeCategoryService {
}
