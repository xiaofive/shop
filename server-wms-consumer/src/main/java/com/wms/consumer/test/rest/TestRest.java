package com.wms.consumer.test.rest;

import com.wms.consumer.feign.NcFeignService;
import com.wms.consumer.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

    @Autowired
    private TestService testService;

    @Autowired
    private NcFeignService ncFeignService;

    @GetMapping(value = "/cs")
    public String hi(@RequestParam String name) {
        return testService.testHelloWorld(name);
    }

    @GetMapping(value = "/csFeign")
    public String csFeign(@RequestParam String name) {
        return ncFeignService.home(name) + "来自feign客户端的调用";
    }

}
