package com.epam.netflix;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service1")
public interface Service1FeignClient {

    @RequestMapping("/hello")
    String helloFromOtherService();
}
