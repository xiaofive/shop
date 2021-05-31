package com.shop.product.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.product.category.bean.entity.SpProductAttribute;
import com.shop.product.category.bean.req.SpProductAttributeReq;
import com.shop.product.category.bean.vo.SpProductAttributeVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface SpProductAttributeService extends IService<SpProductAttribute> {

    /**
     * 新增商品属性
     *
     * @param spProductAttributeReq
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation("新增商品属性")
    @PostMapping(value = "/create")
    public void create(SpProductAttributeReq spProductAttributeReq);

    /**
     * 批量删除商品属性
     *
     * @param ids
     * @return: void
     * @Date: 2021-05-31
     */
    void deleteBatch(List<Long> ids);

    /**
     * 修改商品属性
     *
     * @param spProductAttributeReq
     * @return: void
     * @Date: 2021-05-31
     */
    void update(SpProductAttributeReq spProductAttributeReq);

    /**
     * 分页
     *
     * @param cid
     * @param type
     * @param current
     * @param size
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.category.bean.vo.SpProductAttributeVO>
     * @Date: 2021-05-31
     */
    IPage<SpProductAttributeVO> page(Long cid,
                                     Integer type,
                                     Long current,
                                     Long size);

    /**
     * 根据ID查询商品属性
     *
     * @param id
     * @return: com.shop.product.category.bean.vo.SpProductAttributeVO
     * @Date: 2021-05-31
     */
    SpProductAttributeVO getById(Long id);


}
