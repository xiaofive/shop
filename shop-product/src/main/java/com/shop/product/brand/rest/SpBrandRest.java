package com.shop.product.brand.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.product.brand.bean.req.SpBrandReq;
import com.shop.product.brand.bean.vo.SpBrandVO;
import com.shop.product.brand.service.SpBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 品牌管理rest
 * Author: wang Y
 * Date: 2021-05-31
 */
@Api(tags = "商品品牌")
@RequestMapping("/rest/brand")
@RestController
public class SpBrandRest {

    @Resource
    private SpBrandService spBrandService;

    /**
     * 新增品牌
     *
     * @param spBrandReq
     * @Param:
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "新增品牌")
    @PostMapping(value = "/create")
    public void create(@Valid @RequestBody SpBrandReq spBrandReq) {
        spBrandService.create(spBrandReq);
    }

    /**
     * 根据ID删除品牌
     *
     * @param id
     * @Param:
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "根据ID删除品牌")
    @DeleteMapping(value = "/deleteById")
    public void deleteById(@RequestParam("id") Long id) {
        spBrandService.deleteById(id);
    }

    /**
     * 根据ID批量删除品牌
     *
     * @param idList
     * @Param:
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "根据ID批量删除品牌")
    @DeleteMapping(value = "/deleteBatch")
    public void deleteBatch(@RequestParam("idList") List<Long> idList) {
        spBrandService.deleteBatch(idList);
    }


    /**
     * 根据ID更新品牌
     *
     * @param spBrandReq
     * @Param:
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "根据ID更新品牌")
    @PutMapping(value = "/updateById")
    public void update(@Valid @RequestBody SpBrandReq spBrandReq) {

        if (spBrandReq.getId() == null)
            throw new RuntimeException("id不能为null");
        spBrandService.update(spBrandReq);

    }


    /**
     * 根据ID批量更新厂家制造商状态
     *
     * @param idList
     * @param isBrandManufacturer
     * @Param:
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "批量更新厂家制造商状态")
    @PutMapping(value = "/update/isBrandManufacturer")
    public void updateBatchIsBrandManufacturerStatus(@RequestParam("idList") List<Long> idList, @RequestParam("isBrandManufacturer") Boolean isBrandManufacturer) {
        spBrandService.updateBatchIsBrandManufacturerStatus(idList, isBrandManufacturer);
    }


    /**
     * 根据ID批量启用禁用
     *
     * @param idList
     * @param enable
     * @return: void
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "批量启用禁用")
    @PutMapping(value = "/updateBatchEnable")
    public void updateBatchEnable(@RequestParam("idList") List<Long> idList,
                                  @RequestParam("enable") Boolean enable) {
        spBrandService.updateBatchEnable(idList, enable);
    }


    /**
     * 分页
     *
     * @param current
     * @param size
     * @param keyword
     * @Param:
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.brand.bean.vo.SpBrandVO>
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "根据品牌名称模糊分页获取品牌列表")
    @GetMapping(value = "/page")
    public IPage<SpBrandVO> page(@RequestParam(value = "current", defaultValue = "1") Long current,
                                 @RequestParam(value = "size", defaultValue = "15") Long size,
                                 @RequestParam(value = "brandName", required = false) String brandName) {
        return spBrandService.page(current, size, brandName);
    }

    /**
     * 根据ID查询品牌信息
     *
     * @param id
     * @Param:
     * @return: com.shop.product.brand.bean.vo.SpBrandVO
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "根据ID查询品牌信息")
    @GetMapping(value = "/getById")
    public SpBrandVO getById(@RequestParam("id") Long id) {
        return spBrandService.getById(id);
    }


    /**
     * 获取全部品牌数据
     *
     * @Param:
     * @return: java.util.List<com.shop.product.brand.bean.vo.SpBrandVO>
     * @Date: 2021-05-31
     */
    @ApiOperation(value = "获取全部品牌数据")
    @GetMapping(value = "/listAll")
    public List<SpBrandVO> listAll() {
        return spBrandService.listAll();
    }


}
