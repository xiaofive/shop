package com.shop.common.standard;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.bean.base.BaseEntity;
import com.shop.common.bean.mapper.ShopBaseMapper;

import java.util.ArrayList;
import java.util.List;

public class AbstractSelect<V extends SPSelectorVORule, M extends ShopBaseMapper> {

    public <O extends BaseEntity> List<DefaultSelectVO> buildDefaultSelectorVO(List<V> objs, M mp) {
        List<DefaultSelectVO> dvos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(objs)) {
            for (Object obj : objs) {
                DefaultSelectVO defaultSelectVO = new DefaultSelectVO();
                defaultSelectVO.setFkey(((SPSelectorVORule) obj).getFkey().toString())
                        .setFvalue(((SPSelectorVORule) obj).getFvalue())
                        .setRefId(((SPSelectorVORule) obj).getRefId().toString());
                Page<O> page = new Page<>(1, 15);
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.like("name","");
                mp.selectPage(page,queryWrapper);
            }
        }
        return dvos;
    }

}
