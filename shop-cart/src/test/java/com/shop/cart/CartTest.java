package com.shop.cart;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.shop.cart.cart.bean.dto.SpCartCacheDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车相关测试
 * <p>
 * Author: wang Y
 * Date: 2021-06-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CartTest {

    @Test
    public void buildCookieCarts() {

        SpCartCacheDTO spCartCacheDTO = new SpCartCacheDTO();
        spCartCacheDTO.setAddPrice(new BigDecimal(23.56))
                .setQty(1)
                .setProductId(33L)
                .setSkuId(33L);

        SpCartCacheDTO spCartCacheDTOTwo = new SpCartCacheDTO();
        spCartCacheDTOTwo.setAddPrice(new BigDecimal(23.56))
                .setQty(1)
                .setProductId(55L)
                .setSkuId(55L);

        List<SpCartCacheDTO> carts = Lists.newLinkedList();
        carts.add(spCartCacheDTO);
        carts.add(spCartCacheDTOTwo);

        log.info(JSON.toJSONString(carts));


    }


}
