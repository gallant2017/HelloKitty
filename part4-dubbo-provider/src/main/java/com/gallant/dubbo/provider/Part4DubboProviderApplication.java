package com.gallant.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

//@SpringBootApplication
public class Part4DubboProviderApplication {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]
				{"provider.xml"});
		context.start();
		System.in.read(); // 按任意键退出

		//SpringApplication.run(Part4DubboProviderApplication.class, args);
	}

}
