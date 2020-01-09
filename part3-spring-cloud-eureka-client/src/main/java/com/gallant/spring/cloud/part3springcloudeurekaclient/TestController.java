package com.gallant.spring.cloud.part3springcloudeurekaclient;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Huang Junhao
 * @Date: 2020/1/9 10:30
 */
@RestController
public class TestController {

    @Value("${server.port}")
    String port;

    @GetMapping(value = {"/test"})
    public String test() {
        return "hello,I'm from client1:"+port;
    }
}
