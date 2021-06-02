package com.shop.product.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.product.product.bean.entity.SpProduct;
import com.shop.product.product.bean.req.SpProductAddReq;
import org.springframework.web.bind.annotation.RequestBody;

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
