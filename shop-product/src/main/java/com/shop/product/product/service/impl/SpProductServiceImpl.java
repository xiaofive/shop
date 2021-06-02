package com.shop.product.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
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

    @Override
    public void create(SpProductAddReq spProductAddReq) {
        SpProduct spProduct = BeanConvertUtils.map(spProductAddReq, SpProduct.class);
        spProductMapper.insert(spProduct);
        //库存
        //属性
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
