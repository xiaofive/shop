package com.shop.activity.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.activity.activity.bean.entity.SpActivity;
import com.shop.activity.activity.bean.entity.SpActivityCondition;
import com.shop.activity.activity.bean.entity.SpActivityRule;
import com.shop.activity.activity.bean.req.SpActivityReq;
import com.shop.activity.activity.mapper.SpActivityConditionMapper;
import com.shop.activity.activity.mapper.SpActivityMapper;
import com.shop.activity.activity.mapper.SpActivityRuleMapper;
import com.shop.activity.activity.service.SpActivityService;
import com.shop.common.util.BeanConvertUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpActivityServiceImpl extends ServiceImpl<SpActivityMapper, SpActivity> implements SpActivityService {

    @Resource
    private SpActivityMapper spActivityMapper;

    @Resource
    private SpActivityRuleMapper spActivityRuleMapper;

    @Resource
    private SpActivityConditionMapper spActivityConditionMapper;

    @Override
    public void create(SpActivityReq spActivityReq) {
        //TODO 事务
        //活动
        SpActivity spActivity = BeanConvertUtils.map(spActivityReq,SpActivity.class);
        int activityId = spActivityMapper.insert(spActivity);
        Long spActivityId = Integer.valueOf(activityId).longValue();
        //活动规则
        SpActivityRule spActivityRule = BeanConvertUtils.map(spActivityReq.getSpActivityRuleReq(),SpActivityRule.class);
        spActivityRule.setActivityId(spActivityId);
        spActivityRuleMapper.insert(spActivityRule);
        //活动范围
        SpActivityCondition spActivityCondition = BeanConvertUtils.map(spActivityReq.getSpActivityConditionReq(),SpActivityCondition.class);
        spActivityCondition.setActivityId(spActivityId);
        spActivityConditionMapper.insert(spActivityCondition);
    }

}
