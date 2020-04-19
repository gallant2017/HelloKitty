package com.gallant.dubbo.provider.api.impl;


import com.gallant.dubbo.common.DemoService;

/**
 * Created by huangjunhao on 20/4/19.
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayFuck(String userName) {
        return "fuck you," + userName + "### this is from provider";
    }
}
