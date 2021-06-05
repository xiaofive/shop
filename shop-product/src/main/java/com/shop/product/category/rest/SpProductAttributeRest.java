package com.shop.product.category.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.product.category.bean.req.SpProductAttributeReq;
import com.shop.product.category.bean.vo.SpProductAttributeVO;
import com.shop.product.category.service.SpProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 商品属性管理Rest
 * Author: wang Y
 * Date: 2021-05-31
 */
@Api(tags = "商品属性")
@RestController
@RequestMapping("/rest/productAttribute")
public class SpProductAttributeRest {

    @Resource
    private SpProductAttributeService spProductAttributeService;

    /**
     * 新增商品属性
     * 属性分类ID必须挂靠在已有商品属性分类上
     * type必须选中的
     *
     * @param spProductAttributeReq
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation("新增商品属性")
    @PostMapping(value = "/create")
    public void create(@Valid @RequestBody SpProductAttributeReq spProductAttributeReq) {
        spProductAttributeService.create(spProductAttributeReq);
    }

    /**
     * 批量删除商品属性
     *
     * @param idList
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation("批量删除商品属性")
    @DeleteMapping(value = "/deleteBatch")
    public void deleteBatch(@RequestParam("idList") List<Long> idList) {
        spProductAttributeService.deleteBatch(idList);
    }

    /**
     * 修改商品属性
     *
     * @param spProductAttributeReq
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation("修改商品属性")
    @PutMapping(value = "/updateById")
    public void update(@Valid @RequestBody SpProductAttributeReq spProductAttributeReq) {
        if (spProductAttributeReq.getId() == null)
            throw new RuntimeException("ID不能为空");
        spProductAttributeService.update(spProductAttributeReq);
    }


    /**
     * 根据分类查询属性列表或参数列表
     *
     * @param cid
     * @param type
     * @param current
     * @param size
     * @return: CommonResult<CommonPage < PmsProductAttribute>>
     * @Date: 2021-05-31
     */
    @ApiOperation("根据分类查询属性列表或参数列表")
    @GetMapping(value = "/page")
    public IPage<SpProductAttributeVO> page(@RequestParam("productAttributeCategoryId") Long productAttributeCategoryId,
                                            @RequestParam("type") Integer type, //0,1
                                            @RequestParam(value = "current", defaultValue = "1") Long current,
                                            @RequestParam(value = "size", defaultValue = "15") Long size) {
        if (type != 0 && type != 1)
            throw new RuntimeException("类型只能为0:属性，1：参数");
        IPage<SpProductAttributeVO> iPage = spProductAttributeService.page(productAttributeCategoryId, type, current, size);
        return iPage;
    }

    /**
     * 根据ID查询商品属性
     *
     * @param id
     * @return: CommonResult<PmsProductAttribute>
     * @Date: 2021-05-31
     */
    @ApiOperation("查询单个商品属性")
    @GetMapping(value = "getById")
    public SpProductAttributeVO getById(@RequestParam("id") Long id) {
        return spProductAttributeService.getById(id);
    }

}
