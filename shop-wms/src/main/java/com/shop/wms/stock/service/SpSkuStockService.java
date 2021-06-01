package com.shop.wms.stock.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.wms.stock.bean.SpSkuStockVO;
import com.shop.wms.stock.bean.entity.SpSkuStock;
import com.shop.wms.stock.bean.req.SpSkuStockReq;

import java.util.List;

public interface SpSkuStockService extends IService<SpSkuStock> {

    /**
     * 新增SKU
     *
     * @param spSkuStockReq
     * @return: void
     * @Date: 2021-06-01
     */
    void create(SpSkuStockReq spSkuStockReq);

    /**
     * 根据ID删除SKU
     *
     * @param id
     * @return: void
     * @Date: 2021-06-01
     */
    void deleteById(Long id);

    /**
     * 根据ID批量删除SKU
     *
     * @param idList
     * @return: void
     * @Date: 2021-06-01
     */
    void deleteBatch(List<Long> idList);

    /**
     * 根据ID更新SKU
     *
     * @param spSkuStockReq
     * @return: void
     * @Date: 2021-06-01
     */
    void update(SpSkuStockReq spSkuStockReq);

    /**
     * 分页
     *
     * @param current
     * @param size
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<SpBrandVO>
     * @Date: 2021-06-01
     */
    IPage<SpSkuStockVO> page(Long current, Long size);

    /**
     * 根据ID查询SKU信息
     *
     * @param id
     * @return: com.shop.wms.stock.bean.SpSkuStockVO
     * @Date: 2021-06-01
     */
    SpSkuStockVO getById(Long id);

}
