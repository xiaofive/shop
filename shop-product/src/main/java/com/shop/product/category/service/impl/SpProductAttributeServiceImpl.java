package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.category.bean.SpEnum;
import com.shop.product.category.bean.entity.SpProductAttribute;
import com.shop.product.category.bean.entity.SpProductAttributeCategory;
import com.shop.product.category.bean.req.SpProductAttributeReq;
import com.shop.product.category.bean.vo.SpProductAttributeVO;
import com.shop.product.category.mapper.SpProductAttributeCategoryMapper;
import com.shop.product.category.mapper.SpProductAttributeMapper;
import com.shop.product.category.service.SpProductAttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpProductAttributeServiceImpl extends ServiceImpl<SpProductAttributeMapper, SpProductAttribute> implements SpProductAttributeService {

    @Resource
    private SpProductAttributeMapper spProductAttributeMapper;

    @Resource
    private SpProductAttributeCategoryMapper spProductAttributeCategoryMapper;

    @Override
    public void create(SpProductAttributeReq spProductAttributeReq) {
        SpProductAttribute spProductAttribute = BeanConvertUtils.map(spProductAttributeReq, SpProductAttribute.class);
        spProductAttributeMapper.insert(spProductAttribute);

        //fix商品属性分类统计数量
        fixProductAttributeCategoryNum(spProductAttributeReq.getProductAttributeCategoryId()
                , spProductAttributeReq.getType(),
                1);

    }

    @Override
    public void deleteBatch(List<Long> idList) {
        List<SpProductAttribute> olds = spProductAttributeMapper.selectBatchIds(idList);
        spProductAttributeMapper.deleteBatchIds(idList);

        //fix商品属性分类统计数量
        olds.stream().forEach(spProductAttribute -> {

            fixProductAttributeCategoryNum(spProductAttribute.getProductAttributeCategoryId()
                    , spProductAttribute.getType(),
                    -1);

        });

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

    /**
     * 更新属性分类中的属性数量统计字段
     *
     * @param productAttributeCategoryId 商品属性分类ID
     * @param type                       属性类型
     * @param offset                     数值
     * @return: void
     * @Date: 2021-06-05
     */
    void fixProductAttributeCategoryNum(Long productAttributeCategoryId, Integer type, Integer offset) {

        SpProductAttributeCategory old = spProductAttributeCategoryMapper.selectById(productAttributeCategoryId);
        if (type == SpEnum.ATTRIBUTE_SPEC.getCode()) {
            old.setAttributeNum(old.getAttributeNum() + offset);
        } else {
            old.setParamNum(old.getParamNum() + offset);
        }
        spProductAttributeCategoryMapper.updateById(old);
    }


}
