package com.example.demo;

import com.gallant.dubbo.common.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class Part4DubboConsumerApplication {

	public static void main(String[] args) {
		//SpringApplication.run(Part4DubboConsumerApplication.class, args);

		ClassPathXmlApplicationContext ac =
				new ClassPathXmlApplicationContext("consumer.xml");
		final DemoService demoService = (DemoService) ac.getBean("DemoService");
		System.out.println(demoService.sayFuck("super,man"));
	}

}
