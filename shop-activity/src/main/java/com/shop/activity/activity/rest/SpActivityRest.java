package com.shop.activity.activity.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.shop.activity.activity.bean.entity.SpActivity;
import com.shop.activity.activity.bean.req.SpActivityReq;
import com.shop.activity.activity.service.SpActivityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * 行销活动管理
 * <p>
 * Author: wang Y
 * Date: 2021-06-24
 */
@RequestMapping("/rest/activity")
@RestController
public class SpActivityRest {

    @Resource
    private SpActivityService spActivityService;

    @ApiOperation("创建活动")
    @PostMapping("create")
    public void create(@Valid @RequestBody SpActivityReq spActivityReq) {

        //TODO 校验
        spActivityService.create(spActivityReq);
    }

    @ApiOperation("删除活动")
    @DeleteMapping("deleteById")
    public void deleteById(@RequestParam("id") Long id) {

    }

    @ApiOperation("修改活动")
    @PutMapping("updateByID")
    public void updateById(@Valid @RequestBody SpActivity spActivity) {

    }

    @ApiOperation("活动列表")
    @GetMapping("page")
    public IPage<SpActivity> page(@RequestParam(value = "current", defaultValue = "1") Long current,
                                  @RequestParam(value = "size", defaultValue = "16") Long size) {
        return null;
    }

    @ApiOperation("活动详情")
    @GetMapping("infoById")
    public SpActivity infoById(@RequestParam("id") Long id) {
        return null;
    }

    /**
     * 根据商品ID查询行销活动
     *
     * @param productId
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Date: 2021-06-27
     */
    @ApiOperation("查询行销活动")
    @GetMapping("activityByProductId")
    public Map<String, Object> infoByProductId(@RequestParam("productId") Long productId) {
        //Cache，防击穿
        Map<String, Object> dataMap = Maps.newLinkedHashMap();
        dataMap.put("productId", productId);
        dataMap.put("activityInfoList", null);
        return dataMap;
    }


}
