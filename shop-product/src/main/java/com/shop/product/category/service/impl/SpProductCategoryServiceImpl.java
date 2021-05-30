package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.product.category.bean.entity.SpProductCategory;
import com.shop.product.category.mapper.SpProductCategoryMapper;
import com.shop.product.category.service.SpProductCategoryService;
import org.springframework.stereotype.Service;

@Service
public class SpProductCategoryServiceImpl extends ServiceImpl<SpProductCategoryMapper, SpProductCategory> implements SpProductCategoryService {
}
