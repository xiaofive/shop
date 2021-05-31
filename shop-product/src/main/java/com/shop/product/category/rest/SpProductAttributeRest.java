package com.shop.product.category.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.product.category.bean.req.SpProductAttributeReq;
import com.shop.product.category.bean.vo.SpProductAttributeVO;
import com.shop.product.category.service.SpProductAttributeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 属性管理Rest
 * Author: wang Y
 * Date: 2021-05-31
 */
@RestController
@RequestMapping("/rest/productAttribute")
public class SpProductAttributeRest {

    @Resource
    private SpProductAttributeService spProductAttributeService;

    /**
     * 新增商品属性
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
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @GetMapping(value = "/page")
    public IPage<SpProductAttributeVO> page(@RequestParam("cid") Long cid,
                                            @RequestParam("type") Integer type,
                                            @RequestParam(value = "current", defaultValue = "1") Long current,
                                            @RequestParam(value = "size", defaultValue = "15") Long size) {
        IPage<SpProductAttributeVO> iPage = spProductAttributeService.page(cid, type, current, size);
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
