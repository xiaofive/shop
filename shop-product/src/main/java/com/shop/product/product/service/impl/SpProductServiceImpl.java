package com.shop.product.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.category.bean.entity.SpProductAttributeValue;
import com.shop.product.category.bean.req.SpProductAttributeValueReq;
import com.shop.product.category.mapper.SpProductAttributeValueMapper;
import com.shop.product.discount.bean.entity.SpMemberPrice;
import com.shop.product.discount.bean.entity.SpProductFullReduction;
import com.shop.product.discount.bean.entity.SpProductLadder;
import com.shop.product.discount.bean.req.SpMemberPriceReq;
import com.shop.product.discount.bean.req.SpProductFullReductionReq;
import com.shop.product.discount.bean.req.SpProductLadderReq;
import com.shop.product.discount.mapper.SpMemberPriceMapper;
import com.shop.product.discount.mapper.SpProductFullReductionMapper;
import com.shop.product.discount.mapper.SpProductLadderMapper;
import com.shop.product.product.bean.entity.SpProduct;
import com.shop.product.product.bean.req.SpProductAddReq;
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
    private SpProductLadderMapper spProductLadderMapper;

    @Resource
    private SpProductFullReductionMapper spProductFullReductionMapper;

    @Resource
    private SpMemberPriceMapper spMemberPriceMapper;

    @Resource
    private SpProductAttributeValueMapper spProductAttributeValueMapper;

    @Override
    public void create(SpProductAddReq spProductAddReq) {
        SpProduct spProduct = BeanConvertUtils.map(spProductAddReq, SpProduct.class);
        //商品基本信息
        int productId = spProductMapper.insert(spProduct);
        //满减金额
        List<SpProductLadderReq> spProductLadderReqs = spProductAddReq.getSpProductLadderReqs();
        List<SpProductLadder> spProductLadders = BeanConvertUtils.listMap(spProductLadderReqs, SpProductLadder.class);
        spProductLadders.stream().forEach(spProductLadder -> spProductLadderMapper.insert(spProductLadder));
        //满减折扣
        List<SpProductFullReductionReq> spProductFullReductionReqs = spProductAddReq.getSpProductFullReductionReqs();
        List<SpProductFullReduction> spProductFullReductions = BeanConvertUtils.listMap(spProductFullReductionReqs, SpProductFullReduction.class);
        spProductFullReductions.stream().forEach(spProductFullReduction -> spProductFullReductionMapper.insert(spProductFullReduction));
        //会员价
        List<SpMemberPriceReq> spMemberPriceReqs = spProductAddReq.getSpMemberPriceReqs();
        List<SpMemberPrice> spMemberPrices = BeanConvertUtils.listMap(spMemberPriceReqs, SpMemberPrice.class);
        spMemberPrices.stream().forEach(spMemberPrice -> spMemberPriceMapper.insert(spMemberPrice));
        //属性值
        List<SpProductAttributeValueReq> spProductAttributeReqs = spProductAddReq.getSpProductAttributeValueReqs();
        List<SpProductAttributeValue> spProductAttributeValues = BeanConvertUtils.listMap(spProductAttributeReqs, SpProductAttributeValue.class);
        spProductAttributeValues.stream().forEach(spProductAttributeValue -> spProductAttributeValueMapper.insert(spProductAttributeValue));
        //库存
    }

    @Override
    public void updateApproveStatus(List<Long> idList, Integer approveStatus) {

    }

    @Override
    public void updateisSelfStatus(List<Long> idList, Boolean isSelf) {

    }

    @Override
    public void updateIsRecommend(List<Long> idList, Boolean isRecommend) {

    }

    @Override
    public void updateIsNew(List<Long> idList, Boolean isNew) {

    }


}
