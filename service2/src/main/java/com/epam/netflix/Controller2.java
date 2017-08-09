package com.epam.netflix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller2 {

    public static final String OTHER_SERVICE_NAME = "SERVICE1";
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    Service1FeignClient service1FeignClient;

    @RequestMapping("/serviceInfo")
    public List<ServiceInstance> getAvailableServices() {
      return discoveryClient.getInstances(OTHER_SERVICE_NAME);
    }

    @RequestMapping("/service1Call")
    @HystrixCommand(fallbackMethod = "notWorking")
    public String service1Call() {
        return service1FeignClient.helloFromOtherService();
    }

    private String notWorking() {
        return "Service 1 not available, we are working on this issue with very high priority etc";
    }
}
