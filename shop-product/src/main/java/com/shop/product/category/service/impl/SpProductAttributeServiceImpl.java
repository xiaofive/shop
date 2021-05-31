package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.category.bean.entity.SpProductAttribute;
import com.shop.product.category.bean.req.SpProductAttributeReq;
import com.shop.product.category.bean.vo.SpProductAttributeVO;
import com.shop.product.category.mapper.SpProductAttributeMapper;
import com.shop.product.category.service.SpProductAttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpProductAttributeServiceImpl extends ServiceImpl<SpProductAttributeMapper, SpProductAttribute> implements SpProductAttributeService {

    @Resource
    private SpProductAttributeMapper spProductAttributeMapper;

    @Override
    public void create(SpProductAttributeReq spProductAttributeReq) {
        SpProductAttribute spProductAttribute = BeanConvertUtils.map(spProductAttributeReq, SpProductAttribute.class);
        spProductAttributeMapper.insert(spProductAttribute);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        spProductAttributeMapper.deleteBatchIds(ids);
    }

    @Override
    public void update(SpProductAttributeReq spProductAttributeReq) {
        SpProductAttribute spProductAttribute = BeanConvertUtils.map(spProductAttributeReq, SpProductAttribute.class);
        spProductAttributeMapper.updateById(spProductAttribute);
    }

    @Override
    public IPage<SpProductAttributeVO> page(Long cid, Integer type, Long current, Long size) {
        Page<SpProductAttribute> page = new Page<>(current, size);
        IPage<SpProductAttribute> iPage = spProductAttributeMapper.selectPage(page, null);
        return iPage.convert(b -> BeanConvertUtils.map(b, SpProductAttributeVO.class));
    }

    @Override
    public SpProductAttributeVO getById(Long id) {
        SpProductAttribute spProductAttribute = spProductAttributeMapper.selectById(id);
        SpProductAttributeVO spProductAttributeVO = BeanConvertUtils.map(spProductAttribute, SpProductAttributeVO.class);
        return spProductAttributeVO;
    }


}
