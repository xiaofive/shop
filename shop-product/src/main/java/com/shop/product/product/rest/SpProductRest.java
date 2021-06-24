package com.shop.product.product.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.product.product.bean.req.SpProductAddReq;
import com.shop.product.product.bean.vo.SpProductVO;
import com.shop.product.product.service.SpProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品后台管理rest
 * <p>
 * Author: wang Y
 * Date: 2021-06-01
 */
@Api(tags = "商品管理")
@RequestMapping("/rest/product")
@RestController
public class SpProductRest {

    @Resource
    private SpProductService spProductService;

    /**
     * 新增商品
     *
     * @param spProductAddReq
     * @return: void
     * @Date: 2021-06-01
     */
    @ApiOperation("新增商品")
    @PostMapping(value = "/create")
    public void create(@RequestBody SpProductAddReq spProductAddReq) {
        spProductService.create(spProductAddReq);
    }

    /**
     * 分页 TODO 多条件
     * APP:基于ES的商品搜索 列表仅展示部分信息
     *
     * @param current
     * @param size
     * @return: void
     * @Date: 2021-06-24
     */
    @ApiOperation("商品列表")
    @GetMapping(value = "page")
    public IPage<SpProductVO> page(@RequestParam(value = "current", defaultValue = "1") Long current,
                                   @RequestParam(value = "size", defaultValue = "15") Long size) {
        IPage<SpProductVO> page = spProductService.page(current, size);
        return page;
    }

    /**
     * 商品详情
     * TODO 属性
     * TODO 营销相关
     * TODO 库存相关
     * TODO 规格相关
     * <p>
     * * APP:
     * * 聚合 多线程
     * * cache
     * * 热点redis
     *
     * @param productId
     * @return: void
     * @Date: 2021-06-24
     */
    @ApiOperation("商品详情")
    @GetMapping(value = "info")
    public SpProductVO info(@RequestParam("id") Long productId) {
        return spProductService.info(productId);
    }

    /**
     * 修改审核状态
     *
     * @param idList
     * @param approveStatus
     * @return: void
     * @Date: 2021-06-02
     */
    @ApiOperation("修改审核状态")
    @PutMapping(value = "/update/approveStatus")
    public void updateApproveStatus(@RequestBody List<Long> idList, @RequestParam("approveStatus") Integer approveStatus) {
        spProductService.updateApproveStatus(idList, approveStatus);
    }

    /**
     * 上下架
     *
     * @param idList
     * @param isSelf
     * @return: void
     * @Date: 2021-06-02
     */
    @ApiOperation("上下架")
    @PutMapping(value = "/update/isSelf")
    public void updateisSelfStatus(@RequestBody List<Long> idList, @RequestParam("isSelf") Boolean isSelf) {
        spProductService.updateisSelfStatus(idList, isSelf);
    }

    /**
     * 是否推荐
     *
     * @param idList
     * @param isRecommend
     * @return: void
     * @Date: 2021-06-02
     */
    @ApiOperation("是否推荐")
    @PutMapping(value = "/update/isRecommend")
    public void updateIsRecommend(@RequestBody List<Long> idList, @RequestParam("isRecommend") Boolean isRecommend) {
        spProductService.updateIsRecommend(idList, isRecommend);
    }

    /**
     * 是否新品
     *
     * @param idList
     * @param isNew
     * @return: void
     * @Date: 2021-06-02
     */
    @ApiOperation("是否新品")
    @PutMapping(value = "/update/isNew")
    public void updateIsNew(@RequestBody List<Long> idList, @RequestParam("isNew") Boolean isNew) {
        spProductService.updateIsNew(idList, isNew);
    }

}
