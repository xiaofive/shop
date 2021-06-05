package com.shop.product.brand.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.brand.bean.entity.SpBrand;
import com.shop.product.brand.bean.req.SpBrandReq;
import com.shop.product.brand.bean.vo.SpBrandVO;
import com.shop.product.brand.mapper.SpBrandMapper;
import com.shop.product.brand.service.SpBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class SpBrandServiceImpl extends ServiceImpl<SpBrandMapper, SpBrand> implements SpBrandService {

    @Resource
    private SpBrandMapper spBrandMapper;

    @Override
    public void create(SpBrandReq spBrandReq) {
        SpBrand spBrand = BeanConvertUtils.map(spBrandReq, SpBrand.class);
        spBrandMapper.insert(spBrand);
    }

    @Override
    public void deleteById(Long id) {
        spBrandMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> idList) {
        spBrandMapper.deleteBatchIds(idList);
    }

    @Override
    public void update(SpBrandReq spBrandReq) {
        SpBrand spBrand = BeanConvertUtils.map(spBrandReq, SpBrand.class);
        spBrandMapper.updateById(spBrand);
    }

    @Override
    public void updateBatchIsBrandManufacturerStatus(List<Long> idList, Boolean isBrandManufacturer) {
        QueryWrapper<SpBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        List<SpBrand> olds = spBrandMapper.selectList(queryWrapper);
        olds.stream().forEach(old -> {
            old.setIsBrandManufacturer(isBrandManufacturer);
            spBrandMapper.updateById(old);
        });
    }

    @Override
    public void updateBatchEnable(List<Long> idList, Boolean enable) {
        QueryWrapper<SpBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        List<SpBrand> olds = spBrandMapper.selectList(queryWrapper);
        olds.stream().forEach(old -> {
            old.setEnable(enable);
            spBrandMapper.updateById(old);
        });
    }

    @Override
    public IPage<SpBrandVO> page(Long current, Long size, String brandName) {
        Page<SpBrand> page = new Page<>(current, size);
        QueryWrapper<SpBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", brandName);
        IPage<SpBrand> iPage = spBrandMapper.selectPage(page, queryWrapper);
        return iPage.convert(b -> BeanConvertUtils.map(b, SpBrandVO.class));
    }

    @Override
    public SpBrandVO getById(Long id) {
        SpBrand spBrand = spBrandMapper.selectById(id);
        SpBrandVO spBrandVO = BeanConvertUtils.map(spBrand, SpBrandVO.class);
        return spBrandVO;
    }

    @Override
    public List<SpBrandVO> listAll() {
        List<SpBrand> spBrands = spBrandMapper.selectList(null);
        if (CollectionUtil.isEmpty(spBrands))
            return Collections.emptyList();
        List<SpBrandVO> spBrandVOS = BeanConvertUtils.listMap(spBrands, SpBrandVO.class);
        return spBrandVOS;
    }
}
