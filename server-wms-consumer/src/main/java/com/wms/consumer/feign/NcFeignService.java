package com.wms.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/***
 *功能描述: 对NC模块的Feign调用封装
 * @author wangyang
 */
@FeignClient("service-jyzt-nc")
public interface NcFeignService {

    @RequestMapping(value = "/hi")
    String home(@RequestParam String name);

}
