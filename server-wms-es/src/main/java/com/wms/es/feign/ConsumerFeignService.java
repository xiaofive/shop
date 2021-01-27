package com.wms.es.feign;

import org.springframework.cloud.openfeign.FeignClient;

/***
 *功能描述: 对Consumer模块的Feign调用封装
 * @author wangyang
 */
@FeignClient("service-jyzt-consumer")
public interface ConsumerFeignService {

}
