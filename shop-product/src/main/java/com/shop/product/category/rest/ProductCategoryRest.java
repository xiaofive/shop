package com.shop.product.category.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.common.standard.DefaultSelectVO;
import com.shop.product.category.bean.req.SpProductCategoryReq;
import com.shop.product.category.bean.vo.SpProductCategoryAllTreeVO;
import com.shop.product.category.bean.vo.SpProductCategoryVO;
import com.shop.product.category.service.SpProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品类目后台管理Rest
 * 建议类目层级设置不要过深
 * 本系统为三层 大分类 -> 中分类 ->小分类
 * TODO 批量更新优化 乐观锁
 * Author: wang Y
 * Date: 2021-05-30
 */
@Api(tags = "商品分类")
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
     * @param idList
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("删除商品分类")
    @DeleteMapping(value = "/deleteBatch")
    public void deleteBatch(@RequestBody List<Long> idList) {
        spProductCategoryService.deleteBatch(idList);
    }

    /**
     * 修改分类
     *
     * @param spProductCategoryReq
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("修改商品分类")
    @PutMapping(value = "/update")
    public void update(@Valid @RequestBody SpProductCategoryReq spProductCategoryReq) {
        if (spProductCategoryReq.getId() == null)
            throw new RuntimeException("id不能为null");
        if (spProductCategoryReq.getParentId() < 0) {
            throw new RuntimeException("父类不合法");
        }
        spProductCategoryService.updateById(spProductCategoryReq);
    }

    /**
     * 导航栏显示设置
     *
     * @param idList
     * @param isNav
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("导航栏显示状态")
    @PutMapping(value = "/update/isNav")
    public void updateShowStatus(@RequestBody List<Long> idList,
                                 @RequestParam("isNav") Boolean isNav) {
        spProductCategoryService.updateShowStatus(idList, isNav);
    }

    /**
     * 是否启用
     *
     * @param idList
     * @param enable
     * @return: void
     * @Date: 2021-05-30
     */
    @ApiOperation("类目是否启用")
    @PutMapping(value = "/update/enable")
    public void updateEnable(@RequestBody List<Long> idList,
                             @RequestParam("enable") Boolean enable) {
        spProductCategoryService.updateEnable(idList, enable);
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
                                           @RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
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
    @GetMapping(value = "/getById")
    public SpProductCategoryVO getById(@RequestParam("id") Long id) {
        return spProductCategoryService.getById(id);
    }

    /**
     * 分类树，从根节点开始向下遍历
     *
     * @return: java.util.List<com.shop.product.category.bean.vo.SpProductCategoryAndChildVO>
     * @Date: 2021-05-30
     */
    @ApiOperation("分类树")
    @GetMapping(value = "/tree")
    public List<SpProductCategoryAllTreeVO> listWithTree() {
        return spProductCategoryService.listWithTree();
    }

    /**
     * 属性分类模糊下拉框
     *
     * @param productAttributeCategoryName
     * @return: java.util.List<com.shop.common.standard.DefaultSelectVO>
     * @Date: 2021-06-05
     */
    public List<DefaultSelectVO> select(@RequestParam(value = "productAttributeCategoryName", required = false) String productAttributeCategoryName) {
        spProductCategoryService.select(productAttributeCategoryName);
        return null;
    }

}
