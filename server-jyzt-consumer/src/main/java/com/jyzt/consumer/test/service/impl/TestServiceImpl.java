package com.jyzt.consumer.test.service.impl;

import com.jyzt.consumer.test.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    @Override
    public String testHelloWorld(String name) {  //Feign是对 ribbon的封装，弃用
        return restTemplate.getForObject("http://SERVICE-JYZT-NC/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hey " +
                name + ", there is some problem with hi page,调用失败，被熔断";
    }

}
