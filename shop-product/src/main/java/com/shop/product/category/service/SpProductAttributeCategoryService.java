package com.shop.product.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.product.category.bean.entity.SpProductAttributeCategory;
import com.shop.product.category.bean.req.SpProductAttributeCategoryReq;
import com.shop.product.category.bean.vo.SpProductAttributeCategoryVO;

import java.util.List;

public interface SpProductAttributeCategoryService extends IService<SpProductAttributeCategory> {

    /**
     * 新增属性分类
     *
     * @param spProductAttributeCategoryReq
     * @return: CommonResult
     * @Date: 2021-05-30
     */
    void create(SpProductAttributeCategoryReq spProductAttributeCategoryReq);

    /**
     * 删除商品属性分类
     *
     * @param id
     * @return: void
     * @Date: 2021-05-30
     */
    void deleteById(Long id);

    /**
     * 批量删除属性分类
     *
     * @param ids
     * @return: void
     * @Date: 2021-05-30
     */
    void deleteBatch(List<Long> idList);

    /**
     * 修改商品属性分类名称
     *
     * @param spProductAttributeCategoryReq
     * @return: void
     * @Date: 2021-06-05
     */
    void updateById(SpProductAttributeCategoryReq spProductAttributeCategoryReq);

    /**
     * 分页
     *
     * @param current
     * @param size
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.category.bean.vo.SpProductAttributeCategoryVO>
     * @Date: 2021-05-30
     */
    IPage<SpProductAttributeCategoryVO> page(Long current, Long size);

    /**
     * 根据ID获取商品属性分类信息
     *
     * @param id
     * @return: CommonResult<PmsProductAttributeCategory>
     * @Date: 2021-05-30
     */
    SpProductAttributeCategoryVO getById(Long id);

}
