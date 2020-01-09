package com.gallant.spring.cloud.part3springcloudeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Part3SpringCloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Part3SpringCloudEurekaClientApplication.class, args);
    }

}
