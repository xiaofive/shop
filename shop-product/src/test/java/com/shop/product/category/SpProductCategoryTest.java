package com.shop.product.category;

import com.shop.product.category.service.SpProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 商品分类相关Service测试
 * Author: wang Y
 * Date: 2021-05-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpProductCategoryTest {

    @Resource
    private SpProductCategoryService spProductCategoryService;

    @Test
    public void add() {
        spProductCategoryService.create(null);
    }

}
