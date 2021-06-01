package com.shop.wms.stock.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.wms.stock.bean.SpSkuStockVO;
import com.shop.wms.stock.bean.entity.SpSkuStock;
import com.shop.wms.stock.bean.req.SpSkuStockReq;
import com.shop.wms.stock.mapper.SpSkuStockMapper;
import com.shop.wms.stock.service.SpSkuStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpSkuStockServiceImpl extends ServiceImpl<SpSkuStockMapper, SpSkuStock> implements SpSkuStockService {

    @Resource
    private SpSkuStockMapper spSkuStockMapper;

    @Override
    public void create(SpSkuStockReq spSkuStockReq) {
        SpSkuStock spSkuStock = BeanConvertUtils.map(spSkuStockReq, SpSkuStock.class);
        spSkuStockMapper.insert(spSkuStock);
    }

    @Override
    public void deleteById(Long id) {
        spSkuStockMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> idList) {
        spSkuStockMapper.deleteBatchIds(idList);
    }

    @Override
    public void update(SpSkuStockReq spSkuStockReq) {
        SpSkuStock spSkuStock = BeanConvertUtils.map(spSkuStockReq, SpSkuStock.class);
        spSkuStockMapper.updateById(spSkuStock);
    }

    @Override
    public IPage<SpSkuStockVO> page(Long current, Long size) {
        Page<SpSkuStock> page = new Page<>(current, size);
        IPage<SpSkuStock> iPage = spSkuStockMapper.selectPage(page, null);
        return iPage.convert(b -> BeanConvertUtils.map(b, SpSkuStockVO.class));
    }

    @Override
    public SpSkuStockVO getById(Long id) {
        SpSkuStock spSkuStock = spSkuStockMapper.selectById(id);
        SpSkuStockVO spSkuStockVO = BeanConvertUtils.map(spSkuStock, SpSkuStockVO.class);
        return spSkuStockVO;
    }

}
