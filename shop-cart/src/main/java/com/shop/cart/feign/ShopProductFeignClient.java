package com.shop.cart.feign;

import com.shop.cart.cart.bean.dto.SpProductCacheDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@FeignClient(
        name = "shop-product",
        fallback = ShopProductFeignClient.DefaultFallback.class,
        decode404 = true //避免对正常的业务异常进行熔断处理，可以重写一下Feign的errorDecoder
)
public interface ShopProductFeignClient {


    @GetMapping("/rest/product")
    SpProductCacheDTO getByProductId(Long productId);

    @GetMapping("/rest/product")
    List<SpProductCacheDTO> getProductListByIds(List<Long> productIds);


    /**
     * 降级服务
     * <p>
     * Author: wang Y
     * Date: 2021-06-23
     */
    @Component
    class DefaultFallback implements ShopProductFeignClient {


        @Override
        public SpProductCacheDTO getByProductId(Long productId) {
            System.out.println("getByProductId熔断：");
            return null;
        }

        @Override
        public List<SpProductCacheDTO> getProductListByIds(List<Long> productIds) {
            SpProductCacheDTO spProductCacheDTO = new SpProductCacheDTO();
            spProductCacheDTO
                    .setProductCode("code")
                    .setProductNum(12)
                    .setImg("/image")
                    .setProductName("测试商品")
                    .setUnit("包")
                    .setSelectStatus(1)
                    .setMarketPrice(new BigDecimal("22.24"))
                    .setMaxNum(100)
                    .setProductId(1L);
            System.out.println("getProductListByIds熔断：");
            return Collections.singletonList(spProductCacheDTO);
        }
    }


}
