package com.shop.product.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.category.bean.entity.SpProductCategory;
import com.shop.product.category.bean.req.SpProductCategoryReq;
import com.shop.product.category.bean.vo.SpProductCategoryAllTreeVO;
import com.shop.product.category.bean.vo.SpProductCategoryVO;
import com.shop.product.category.mapper.SpProductCategoryMapper;
import com.shop.product.category.service.SpProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpProductCategoryServiceImpl extends ServiceImpl<SpProductCategoryMapper, SpProductCategory> implements SpProductCategoryService {

    @Resource
    private SpProductCategoryMapper spProductCategoryMapper;

    @Override
    public void create(SpProductCategoryReq spProductCategoryReq) {
        SpProductCategory spProductCategory = BeanConvertUtils.map(spProductCategoryReq, SpProductCategory.class);
        spProductCategoryMapper.insert(spProductCategory);
    }

    @Override
    public void deleteById(Long id) {
        spProductCategoryMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> idList) {
        idList.forEach(this::deleteById);
    }

    @Override
    public void updateById(SpProductCategoryReq spProductCategoryReq) {
        SpProductCategory spProductCategory = BeanConvertUtils.map(spProductCategoryReq, SpProductCategory.class);
        spProductCategoryMapper.updateById(spProductCategory);
    }

    @Override
    public void updateShowStatus(List<Long> idList, Boolean isNav) {
        UpdateWrapper<SpProductCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", idList);

        spProductCategoryMapper.
    }

    @Override
    public void updateEnable(List<Long> idList, Boolean enable) {

    }

    @Override
    public IPage<SpProductCategoryVO> page(Long current, Long size, Long parentId) {
        Page<SpProductCategory> page = new Page<>(current, size);
        UpdateWrapper<SpProductCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("parent_id", parentId);
        IPage<SpProductCategory> spProductCategoryIPage = spProductCategoryMapper.selectPage(page, updateWrapper);
        return spProductCategoryIPage.convert(b -> BeanConvertUtils.map(b, SpProductCategoryVO.class));
    }

    @Override
    public SpProductCategoryVO getById(Long id) {
        SpProductCategory spProductCategory = spProductCategoryMapper.selectById(id);
        SpProductCategoryVO spProductCategoryVO = BeanConvertUtils.map(spProductCategory, SpProductCategoryVO.class);
        return spProductCategoryVO;
    }

    @Override
    public List<SpProductCategoryAllTreeVO> listWithTree() {

        //1.查出所有已启用分类
        QueryWrapper<SpProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enable", true);
        List<SpProductCategory> spProductCategories = spProductCategoryMapper.selectList(queryWrapper);
        List<SpProductCategoryAllTreeVO> spProductCategoryAllTreeVOS = BeanConvertUtils.listMap(spProductCategories, SpProductCategoryAllTreeVO.class);

        //2.组装成父子树结构
        List<SpProductCategoryAllTreeVO> tree = spProductCategoryAllTreeVOS.stream()
                //过滤所有根节点
                .filter(categoryVO -> categoryVO.getParentId() == 0)
                //设置子节点
                .map((menu) -> {
                    menu.setChildNode(getChildNodes(menu, spProductCategoryAllTreeVOS));
                    return menu;
                })
                //进行排序
                .sorted((menu1, menu2) -> {
                    return menu1.getSort() - menu2.getSort();
                }).
                        collect(Collectors.toList());
        return tree;
    }

    private List<SpProductCategoryAllTreeVO> getChildNodes(SpProductCategoryAllTreeVO category, List<SpProductCategoryAllTreeVO> spProductCategoryAllTreeVOS) {
        List<SpProductCategoryAllTreeVO> children = spProductCategoryAllTreeVOS.stream()
                //过滤所有根节点
                .filter(categoryEntity -> categoryEntity.getParentId() == category.getId())
                //设置子节点
                .map(categoryEntity -> {
                    categoryEntity.setChildNode(getChildNodes(categoryEntity, spProductCategoryAllTreeVOS));
                    return categoryEntity;
                })
                //进行排序
                .sorted((menu1, menu2) -> (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort()))
                .collect(Collectors.toList());

        return children;
    }

}
