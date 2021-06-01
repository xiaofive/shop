package com.shop.wms.stock.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.wms.stock.bean.SpSkuStockVO;
import com.shop.wms.stock.bean.req.SpSkuStockReq;
import com.shop.wms.stock.service.SpSkuStockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 库存管理相关
 * 以后可扩展WMS库存管理模块
 * <p>
 * Author: wang Y
 * Date: 2021-06-01
 */
@RequestMapping("/rest/sku/stock")
@RestController
public class SpSkuStockRest {

    @Resource
    private SpSkuStockService spSkuStockService;

    /**
     * 新增SKU
     *
     * @param spSkuStockReq
     * @return: void
     * @Date: 2021-06-01
     */
    @ApiOperation(value = "新增SKU")
    @PostMapping(value = "/create")
    public void create(@Valid @RequestBody SpSkuStockReq spSkuStockReq) {
        spSkuStockService.create(spSkuStockReq);
    }

    /**
     * 根据ID删除SKU
     *
     * @param id
     * @return: void
     * @Date: 2021-06-01
     */
    @ApiOperation(value = "根据ID删除SKU")
    @DeleteMapping(value = "/deleteById")
    public void deleteById(@RequestParam("id") Long id) {
        spSkuStockService.deleteById(id);
    }

    /**
     * 根据ID批量删除SKU
     *
     * @param idList
     * @return: void
     * @Date: 2021-06-01
     */
    @ApiOperation(value = "根据ID批量删除SKU")
    @DeleteMapping(value = "/deleteBatch")
    public void deleteBatch(@RequestParam("idList") List<Long> idList) {
        spSkuStockService.deleteBatch(idList);
    }


    /**
     * 根据ID更新SKU
     *
     * @param spSkuStockReq
     * @return: void
     * @Date: 2021-06-01
     */
    @ApiOperation(value = "根据ID更新SKU")
    @PutMapping(value = "/updateById")
    public void update(@Valid @RequestBody SpSkuStockReq spSkuStockReq) {

        if (spSkuStockReq.getId() == null)
            throw new RuntimeException("id不能为null");
        spSkuStockService.update(spSkuStockReq);
    }

    /**
     * 分页
     *
     * @param current
     * @param size
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<SpBrandVO>
     * @Date: 2021-06-01
     */
    @ApiOperation(value = "SKUd分页列表")
    @GetMapping(value = "/page")
    public IPage<SpSkuStockVO> page(@RequestParam(value = "current", defaultValue = "1") Long current,
                                    @RequestParam(value = "size", defaultValue = "15") Long size) {
        return spSkuStockService.page(current, size);
    }

    /**
     * 根据ID查询SKU信息
     *
     * @param id
     * @return: com.shop.wms.stock.bean.SpSkuStockVO
     * @Date: 2021-06-01
     */
    @ApiOperation(value = "根据ID查询SKU信息")
    @GetMapping(value = "/getById")
    public SpSkuStockVO getById(@RequestParam("id") Long id) {
        return spSkuStockService.getById(id);
    }


}
