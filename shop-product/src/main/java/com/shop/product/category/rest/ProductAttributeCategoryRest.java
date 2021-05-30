package com.shop.product.category.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.product.category.bean.req.SpProductAttributeCategoryReq;
import com.shop.product.category.bean.vo.SpProductAttributeCategoryVO;
import com.shop.product.category.service.SpProductAttributeCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 商品属性分类后台管理Rest
 * Author: wang Y
 * Date: 2021-05-30
 */
@RestController
@RequestMapping("/rest/productAttributeCategory/")
public class ProductAttributeCategoryRest {

    @Resource
    private SpProductAttributeCategoryService spProductAttributeCategoryService;

    /**
     * 新增属性分类
     *
     * @param spProductAttributeCategoryReq
     * @return: CommonResult
     * @Date: 2021-05-30
     */
    @ApiOperation("新增属性分类")
    @PostMapping(value = "create")
    public void create(@Valid @RequestBody SpProductAttributeCategoryReq spProductAttributeCategoryReq) {

    }

    /**
     * 删除商品属性分类
     *
     * @param id
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("删除商品属性分类")
    @DeleteMapping(value = "deleteById")
    public void deleteById(@RequestParam("id") Long id) {
        spProductAttributeCategoryService.deleteById(id);
    }

    /**
     * 批量删除属性分类
     *
     * @param ids
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("批量删除删除商品属性分类")
    @DeleteMapping(value = "deleteBatch")
    public void deleteBatch(@RequestParam("ids") List<Long> ids) {
        spProductAttributeCategoryService.deleteBatch(ids);
    }


    /**
     * 修改商品属性分类
     *
     * @param id
     * @param name
     * @return: CommonResult
     * @Date: 2021-05-30
     */
    @ApiOperation("修改商品属性分类")
    @PutMapping(value = "updateById")
    public void updateById(@RequestParam("id") Long id, @RequestParam("name") String name) {
        spProductAttributeCategoryService.updateById(id, name);
    }

    /**
     * 分页
     *
     * @param current
     * @param size
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.category.bean.vo.SpProductAttributeCategoryVO>
     * @Date: 2021-05-30
     */
    @ApiOperation("分页获取所有商品属性分类")
    @GetMapping(value = "page")
    public IPage<SpProductAttributeCategoryVO> page(@RequestParam(value = "current", defaultValue = "1") Long current,
                                                    @RequestParam(value = "size", defaultValue = "15") Long size) {
        IPage<SpProductAttributeCategoryVO> iPage = spProductAttributeCategoryService.page(current, size);
        return iPage;
    }

    /**
     * 根据ID获取商品属性分类信息
     *
     * @param id
     * @return: CommonResult<PmsProductAttributeCategory>
     * @Date: 2021-05-30
     */
    @ApiOperation("根据ID获取商品属性分类信息")
    @GetMapping(value = "getById")
    public SpProductAttributeCategoryVO getById(@RequestParam("id") Long id) {
        return spProductAttributeCategoryService.getById(id);
    }

    //listAll

}
