package com.gallant.dubbo.provider.api.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.gallant.dubbo.common.DemoService;
import com.gallant.dubbo.provider.api.service.IUserService;
import com.gallant.dubbo.provider.api.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.time.LocalDate;

/**
 * Created by huangjunhao on 20/4/19.
 */
@Service
public class DemoServiceImpl implements DemoService {



    @Override
    public String sayFuck(String userName) {

        ApplicationContext context= ServiceBean.getSpringContext();
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);

        return "fuck you," + userName + "### this is from provider ***" +
                userService.getUserList().size();
    }
}
