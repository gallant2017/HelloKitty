package com.example.demo;

import com.gallant.dubbo.common.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Part4DubboConsumerApplication {

	public static void main(String[] args) throws IOException {


		SpringApplication.run(Part4DubboConsumerApplication.class, args);
//
//		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("consumer.xml");
//		final DemoService demoService = (DemoService) ac.getBean("DemoService");
//		for (int i=0;i<=1000;i++) {
//			System.out.println(demoService.sayFuck("hey"));
//		}
//		System.in.read();
	}

}
