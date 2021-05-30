package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.category.bean.entity.SpProductCategory;
import com.shop.product.category.bean.req.SpProductCategoryReq;
import com.shop.product.category.bean.vo.SpProductCategoryAndChildVO;
import com.shop.product.category.bean.vo.SpProductCategoryVO;
import com.shop.product.category.mapper.SpProductCategoryMapper;
import com.shop.product.category.service.SpProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpProductCategoryServiceImpl extends ServiceImpl<SpProductCategoryMapper, SpProductCategory> implements SpProductCategoryService {

    @Resource
    private SpProductCategoryMapper spProductCategoryMapper;

    @Override
    public void create(SpProductCategoryReq spProductCategoryReq) {
        SpProductCategory spProductCategory = BeanConvertUtils.map(spProductCategoryReq, SpProductCategory.class);
        spProductCategoryMapper.insert(spProductCategory);
    }

    @Override
    public void deleteById(Long id) {
        spProductCategoryMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public void update(Long id, SpProductCategoryReq spProductCategoryReq) {
        SpProductCategory spProductCategory = BeanConvertUtils.map(spProductCategoryReq, SpProductCategory.class);
        spProductCategoryMapper.updateById(spProductCategory);
    }

    @Override
    public void updateShowStatus(List<Long> ids, Integer navStatus) {
        UpdateWrapper<SpProductCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);
        //spProductCategoryMapper.u TODO
    }

    @Override
    public void updateEnable(List<Long> ids, Boolean enable) {

    }

    @Override
    public IPage<SpProductCategoryVO> page(Long current, Long size, Long parentId) {
        Page<SpProductCategory> page = new Page<>(current, size);
        IPage<SpProductCategory> spProductCategoryIPage = spProductCategoryMapper.selectPage(page, null);
        return spProductCategoryIPage.convert(b -> BeanConvertUtils.map(b, SpProductCategoryVO.class));
    }

    @Override
    public SpProductCategory getById(Long id) {
        return spProductCategoryMapper.selectById(id);
    }

    @Override
    public List<SpProductCategoryAndChildVO> listAll() {
        return null;
    }

}
