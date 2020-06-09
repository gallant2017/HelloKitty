package com.example.demo.service;

import com.gallant.dubbo.common.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommonService {

   public static ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("consumer.xml");
   public static DemoService demoService = (DemoService) ac.getBean("DemoService");

}
