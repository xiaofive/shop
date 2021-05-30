package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.category.bean.entity.SpProductAttributeCategory;
import com.shop.product.category.bean.req.SpProductAttributeCategoryReq;
import com.shop.product.category.bean.vo.SpProductAttributeCategoryVO;
import com.shop.product.category.mapper.SpProductAttributeCategoryMapper;
import com.shop.product.category.service.SpProductAttributeCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpProductAttributeCategoryServiceImpl extends ServiceImpl<SpProductAttributeCategoryMapper, SpProductAttributeCategory> implements SpProductAttributeCategoryService {

    @Resource
    private SpProductAttributeCategoryMapper spProductAttributeCategoryMapper;

    @Override
    public void create(SpProductAttributeCategoryReq spProductAttributeCategoryReq) {
        SpProductAttributeCategory spProductAttributeCategory = BeanConvertUtils.map(spProductAttributeCategoryReq, SpProductAttributeCategory.class);
        spProductAttributeCategoryMapper.insert(spProductAttributeCategory);
    }

    @Override
    public void deleteById(Long id) {
        spProductAttributeCategoryMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public void updateById(Long id, String name) {

    }

    @Override
    public IPage<SpProductAttributeCategoryVO> page(Long current, Long size) {
        Page<SpProductAttributeCategory> page = new Page<>(current, size);
        IPage<SpProductAttributeCategory> iPage = spProductAttributeCategoryMapper.selectPage(page, null);
        return iPage.convert(b -> BeanConvertUtils.map(b, SpProductAttributeCategoryVO.class));
    }

    @Override
    public SpProductAttributeCategoryVO getById(Long id) {
        SpProductAttributeCategory spProductAttributeCategory = spProductAttributeCategoryMapper.selectById(id);
        SpProductAttributeCategoryVO spProductAttributeCategoryVO = BeanConvertUtils.map(spProductAttributeCategory, SpProductAttributeCategoryVO.class);
        return spProductAttributeCategoryVO;
    }

}
