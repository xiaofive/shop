package com.shop.product.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.product.category.bean.entity.SpProductCategory;
import com.shop.product.category.bean.req.SpProductCategoryReq;
import com.shop.product.category.bean.vo.SpProductCategoryAllTreeVO;
import com.shop.product.category.bean.vo.SpProductCategoryVO;

import java.util.List;

/**
 * 商品分类相关接口定义
 * Author: wang Y
 * Date: 2021-05-30
 */
public interface SpProductCategoryService extends IService<SpProductCategory> {

    /**
     * 添加分类
     *
     * @param spProductCategoryReq
     * @return: void
     * @Date: 2021-05-30
     */
    void create(SpProductCategoryReq spProductCategoryReq);

    /**
     * 删除分类
     *
     * @param id
     * @return: void
     * @Date: 2021-05-30
     */
    void deleteById(Long id);

    /**
     * 批量删除
     *
     * @param idList
     * @return: void
     * @Date: 2021-05-30
     */
    void deleteBatch(List<Long> idList);

    /**
     * 修改分类
     *
     * @param spProductCategoryReq
     * @return: void
     * @Date: 2021-05-30
     */
    void updateById(SpProductCategoryReq spProductCategoryReq);

    /**
     * 导航栏显示设置
     *
     * @param idList
     * @param isNav
     * @return: void
     * @Date: 2021-05-30
     */
    void updateShowStatus(List<Long> idList, Boolean isNav);

    /**
     * 是否启用
     *
     * @param idList
     * @param enable
     * @return: void
     * @Date: 2021-05-30
     */
    void updateEnable(List<Long> idList, Boolean enable);

    /**
     * 分页
     *
     * @param current
     * @param size
     * @param parentId
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.category.bean.vo.SpProductCategoryVO>
     * @Date: 2021-05-30
     */
    IPage<SpProductCategoryVO> page(Long current,
                                    Long size,
                                    Long parentId);

    /**
     * 根据id获取商品分类
     *
     * @param id
     * @return: com.shop.product.category.bean.entity.SpProductCategory
     * @Date: 2021-05-30
     */
    SpProductCategoryVO getById(Long id);

    /**
     * 分类树，从根节点开始向下遍历
     *
     * @return: java.util.List<com.shop.product.category.bean.vo.SpProductCategoryAndChildVO>
     * @Date: 2021-05-30
     */
    List<SpProductCategoryAllTreeVO> listWithTree();


}
