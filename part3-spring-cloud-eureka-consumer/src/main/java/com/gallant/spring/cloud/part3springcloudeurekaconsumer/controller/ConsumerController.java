package com.gallant.spring.cloud.part3springcloudeurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Huang Junhao
 * @Date: 2020/1/9 13:58
 */
@RestController
public class ConsumerController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    private String getUrl(String clientApplicationName, String interfaceName) {
        // 使用loadBalancerClient的choose函数来负载均衡的选出一个eurekaClient的服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose(clientApplicationName);
        // 获取之前eurekaClient /all接口地址
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + interfaceName;
        System.out.println(url);
        return url;
    }
    @GetMapping("/hello")
    public String test(){
        RestTemplate restTemplate =new RestTemplate();
        return "consumer-> service :" + restTemplate.getForObject(getUrl("EUREKA-CLIENT-01","/test"), String.class);
    }

}
