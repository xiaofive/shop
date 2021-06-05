package com.shop.product.brand.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.product.brand.bean.entity.SpBrand;
import com.shop.product.brand.bean.req.SpBrandReq;
import com.shop.product.brand.bean.vo.SpBrandVO;

import java.util.List;

public interface SpBrandService extends IService<SpBrand> {


    /**
     * 新增品牌
     *
     * @param spBrandReq
     * @return: void
     * @Date: 2021-05-31
     */
    void create(SpBrandReq spBrandReq);

    /**
     * 根据ID删除品牌
     *
     * @param id
     * @return: void
     * @Date: 2021-05-31
     */
    void deleteById(Long id);

    /**
     * 根据ID批量删除品牌
     *
     * @param idList
     * @return: void
     * @Date: 2021-05-31
     */
    void deleteBatch(List<Long> idList);

    /**
     * 根据ID更新品牌
     *
     * @param spBrandReq
     * @return: void
     * @Date: 2021-05-31
     */
    void update(SpBrandReq spBrandReq);

    /**
     * 根据ID批量更新厂家制造商状态
     *
     * @param idList
     * @param isBrandManufacturer
     * @return: void
     * @Date: 2021-05-31
     */
    void updateBatchIsBrandManufacturerStatus(List<Long> idList, Boolean isBrandManufacturer);

    /**
     * 根据ID批量启用禁用
     *
     * @param idList
     * @param enable
     * @return: void
     * @Date: 2021-05-31
     */
    void updateBatchEnable(List<Long> idList, Boolean enable);

    /**
     * 分页
     *
     * @param current
     * @param size
     * @param brandName
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.brand.bean.vo.SpBrandVO>
     * @Date: 2021-05-31
     */
    IPage<SpBrandVO> page(Long current,
                          Long size,
                          String brandName);

    /**
     * 根据ID查询品牌信息
     *
     * @param id
     * @return: com.shop.product.brand.bean.vo.SpBrandVO
     * @Date: 2021-05-31
     */
    SpBrandVO getById(Long id);

    /**
     * 获取全部品牌数据
     *
     * @return: java.util.List<com.shop.product.brand.bean.vo.SpBrandVO>
     * @Date: 2021-05-31
     */
    List<SpBrandVO> listAll();


}
