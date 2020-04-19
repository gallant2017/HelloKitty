package com.gallant.spring.cloud.part3springcloudeurekaconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Huang Junhao
 * @Date: 2020/1/9 13:58
 */
@RestController
public class ConsumerController {


    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

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

    @HystrixCommand(fallbackMethod = "testBack")
    @GetMapping("/hello")
    public UsersDto test() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getUrl("EUREKA-CLIENT-01", "/test"), UsersDto.class);
    }

    public UsersDto testBack() {
        UsersDto obj = new UsersDto();
        obj.setUserName("托底数据");
        obj.setSex("女");
        obj.setAddrs("潘多拉");
        return obj;
    }

}
