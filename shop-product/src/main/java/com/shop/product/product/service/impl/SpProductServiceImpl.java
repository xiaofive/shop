package com.shop.product.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.category.bean.entity.SpProductAttributeValue;
import com.shop.product.category.bean.req.SpProductAttributeValueReq;
import com.shop.product.category.mapper.SpProductAttributeValueMapper;
import com.shop.product.product.bean.entity.SpProduct;
import com.shop.product.product.bean.req.SpProductAddReq;
import com.shop.product.product.bean.vo.SpProductVO;
import com.shop.product.product.mapper.SpProductMapper;
import com.shop.product.product.service.SpProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpProductServiceImpl extends ServiceImpl<SpProductMapper, SpProduct> implements SpProductService {

    @Resource
    private SpProductMapper spProductMapper;

    @Resource
    private SpProductAttributeValueMapper spProductAttributeValueMapper;

    @Override
    public void create(SpProductAddReq spProductAddReq) {
        SpProduct spProduct = BeanConvertUtils.map(spProductAddReq, SpProduct.class);
        //商品基本信息
        int productId = spProductMapper.insert(spProduct);
        //属性值
        List<SpProductAttributeValueReq> spProductAttributeReqs = spProductAddReq.getSpProductAttributeValueReqs();
        List<SpProductAttributeValue> spProductAttributeValues = BeanConvertUtils.listMap(spProductAttributeReqs, SpProductAttributeValue.class);
        spProductAttributeValues.stream().forEach(spProductAttributeValue -> spProductAttributeValueMapper.insert(spProductAttributeValue));

    }

    @Override
    public IPage<SpProductVO> page(Long current, Long size) {
        Page<SpProduct> page = new Page<>(current, size);
        IPage<SpProduct> spProductIPage = spProductMapper.selectPage(page, null);
        return spProductIPage.convert(b -> BeanConvertUtils.map(b, SpProductVO.class));
    }

    @Override
    public SpProductVO info(Long productId) {
        SpProduct spProduct = spProductMapper.selectById(productId);
        return BeanConvertUtils.map(spProduct, SpProductVO.class);
    }

    @Override
    public void updateApproveStatus(List<Long> idList, Integer approveStatus) {
        QueryWrapper<SpProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        List<SpProduct> olds = spProductMapper.selectList(queryWrapper);
        olds.stream().forEach(old -> {
            old.setApproveStatus(approveStatus);
            spProductMapper.updateById(old);
        });
    }

    @Override
    public void updateisSelfStatus(List<Long> idList, Boolean isSelf) {

        QueryWrapper<SpProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        List<SpProduct> olds = spProductMapper.selectList(queryWrapper);
        olds.stream().forEach(old -> {
            old.setIsSelf(isSelf);
            spProductMapper.updateById(old);
        });

    }

    @Override
    public void updateIsRecommend(List<Long> idList, Boolean isRecommend) {

        QueryWrapper<SpProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        List<SpProduct> olds = spProductMapper.selectList(queryWrapper);
        olds.stream().forEach(old -> {
            old.setIsRecommand(isRecommend);
            spProductMapper.updateById(old);
        });


    }

    @Override
    public void updateIsNew(List<Long> idList, Boolean isNew) {

        QueryWrapper<SpProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        List<SpProduct> olds = spProductMapper.selectList(queryWrapper);
        olds.stream().forEach(old -> {
            old.setIsNew(isNew);
            spProductMapper.updateById(old);
        });

    }


}
