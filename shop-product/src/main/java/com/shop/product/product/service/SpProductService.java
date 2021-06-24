package com.shop.product.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.product.product.bean.entity.SpProduct;
import com.shop.product.product.bean.req.SpProductAddReq;
import com.shop.product.product.bean.vo.SpProductVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SpProductService extends IService<SpProduct> {

    /**
     * 新增商品
     *
     * @param spProductAddReq
     * @return: void
     * @Date: 2021-06-02
     */
    void create(@RequestBody SpProductAddReq spProductAddReq);

    /**
     * 分页
     *
     * @param current
     * @param size
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.product.bean.entity.SpProduct>
     * @Date: 2021-06-24
     */
    IPage<SpProductVO> page(Long current,
                            Long size);

    /**
     * 商品详情
     *
     * @param productId
     * @return: com.shop.product.product.bean.vo.SpProductVO
     * @Date: 2021-06-24
     */
    SpProductVO info(@RequestParam("id") Long productId);

    /**
     * 修改审核状态
     *
     * @param idList
     * @param approveStatus
     * @return: void
     * @Date: 2021-06-02
     */
    void updateApproveStatus(List<Long> idList, Integer approveStatus);

    /**
     * 上下架
     *
     * @param idList
     * @param isSelf
     * @return: void
     * @Date: 2021-06-02
     */
    void updateisSelfStatus(List<Long> idList, Boolean isSelf);

    /**
     * 是否推荐
     *
     * @param idList
     * @param isRecommend
     * @return: void
     * @Date: 2021-06-02
     */
    void updateIsRecommend(List<Long> idList, Boolean isRecommend);

    /**
     * 是否新品
     *
     * @param idList
     * @param isNew
     * @return: void
     * @Date: 2021-06-02
     */
    void updateIsNew(List<Long> idList, Boolean isNew);
}
