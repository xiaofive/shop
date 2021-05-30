package com.shop.product.category.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.product.category.bean.entity.SpProductCategory;
import com.shop.product.category.bean.req.SpProductCategoryReq;
import com.shop.product.category.bean.vo.SpProductCategoryAndChildVO;
import com.shop.product.category.bean.vo.SpProductCategoryVO;
import com.shop.product.category.service.SpProductCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品类目后台管理Rest
 * 建议类目层级设置不要过深
 * 本系统为三层 大分类 -> 中分类 ->小分类
 * Author: wang Y
 * Date: 2021-05-30
 */
@RequestMapping("/rest/product/category")
@RestController
public class ProductCategoryRest {

    @Autowired
    private SpProductCategoryService spProductCategoryService;

    /**
     * 添加分类
     *
     * @param spProductCategoryReq
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("添加分类")
    @PostMapping(value = "/create")
    public void create(@Valid @RequestBody SpProductCategoryReq spProductCategoryReq) {
        if (spProductCategoryReq.getParentId() < 0) {
            throw new RuntimeException("父类不合法");
        }
        spProductCategoryService.create(spProductCategoryReq);

    }

    /**
     * 删除分类
     *
     * @param id
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("删除商品分类")
    @DeleteMapping(value = "/deleteById")
    public void deleteById(@RequestParam("id") Long id) {
        spProductCategoryService.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("删除商品分类")
    @DeleteMapping(value = "/deleteBatch")
    public void deleteBatch(@RequestParam("ids") List<Long> ids) {
        spProductCategoryService.deleteBatch(ids);
    }

    /**
     * 修改分类
     *
     * @param id
     * @param spProductCategoryReq
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("修改商品分类")
    @PutMapping(value = "/update/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody SpProductCategoryReq spProductCategoryReq) {
        spProductCategoryService.update(id, spProductCategoryReq);
    }

    /**
     * 导航栏显示设置
     *
     * @param ids
     * @param navStatus
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("导航栏显示状态")
    @PutMapping(value = "/update/navStatus")
    public void updateShowStatus(@RequestParam("ids") List<Long> ids,
                                @RequestParam("navStatus") Integer navStatus) {
        spProductCategoryService.updateShowStatus(ids, navStatus);
    }

    /**
     * 是否启用
     *
     * @param ids
     * @param enable
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("类目是否启用")
    @PutMapping(value = "/update/enable")
    public void updateEnable(@RequestParam("ids") List<Long> ids,
                             @RequestParam("enable") Boolean enable) {
        spProductCategoryService.updateEnable(ids, enable);
    }

    /**
     * 分页
     *
     * @param current
     * @param size
     * @param parentId
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.category.bean.vo.SpProductCategoryVO>
     * @Date: 2021-05-30
     */
    @ApiOperation("分页查询商品分类")
    @GetMapping(value = "/list")
    public IPage<SpProductCategoryVO> page(@RequestParam(value = "current", defaultValue = "1") Long current,
                                           @RequestParam(value = "size", defaultValue = "15") Long size,
                                           @RequestParam("parentId") Long parentId) {
        if (parentId < 0) {
            throw new RuntimeException("parentId 不合法");
        }
        IPage<SpProductCategoryVO> page = spProductCategoryService.page(current, size, parentId);
        return page;
    }

    /**
     * 根据id获取商品分类
     *
     * @param id
     * @return: com.shop.product.category.bean.entity.SpProductCategory
     * @Date: 2021-05-30
     */
    @ApiOperation("根据id获取商品分类")
    @GetMapping(value = "/{id}")
    public SpProductCategory getById(@RequestParam("id") Long id) {
        return spProductCategoryService.getById(id);
    }

    /**
     * 从根节点向下遍历获取所有分类数据
     *
     * @return: java.util.List<com.shop.product.category.bean.vo.SpProductCategoryAndChildVO>
     * @Date: 2021-05-30
     */
    @ApiOperation("当前分类及其向下遍历所有子分类,无参从根节点开始")
    @GetMapping(value = "/listAll")
    public List<SpProductCategoryAndChildVO> listAll() {
        return spProductCategoryService.listAll();
    }

}
