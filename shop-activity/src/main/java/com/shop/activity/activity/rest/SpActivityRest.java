package com.shop.activity.activity.rest;

import com.shop.activity.activity.service.SpActivityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 行销活动管理
 * <p>
 * Author: wang Y
 * Date: 2021-06-24
 */
@RequestMapping("/rest/activity")
@RestController
public class SpActivityRest {

    @Resource
    private SpActivityService spActivityService;

}
