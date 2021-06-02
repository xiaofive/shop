package com.shop.product.product.rest;

import com.shop.product.product.bean.req.SpProductAddReq;
import com.shop.product.product.service.SpProductService;
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
     * 修改审核状态
     *
     * @param idList
     * @param approveStatus
     * @return: void
     * @Date: 2021-06-02
     */
    @ApiOperation("修改审核状态")
    @PutMapping(value = "/update/approveStatus")
    public void updateApproveStatus(@RequestParam("idList") List<Long> idList, @RequestParam("approveStatus") Integer approveStatus) {
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
    public void updateisSelfStatus(@RequestParam("idList") List<Long> idList, @RequestParam("isSelf") Boolean isSelf) {
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
    public void updateIsRecommend(@RequestParam("idList") List<Long> idList, @RequestParam("isRecommend") Boolean isRecommend) {
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
    public void updateIsNew(@RequestParam("idList") List<Long> idList, @RequestParam("isNew") Boolean isNew) {
        spProductService.updateIsNew(idList, isNew);
    }

}
