package com.gallant.spring.cloud.part3springcloudeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Part3SpringCloudEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Part3SpringCloudEurekaServerApplication.class, args);
    }

}
