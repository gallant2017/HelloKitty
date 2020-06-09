package com.gallant.dubbo.spi.spi.impl;

import com.gallant.dubbo.spi.spi.IFuck;
import com.gallant.dubbo.spi.spi.ProxyFactory;

public class test {

    public static void main(String[] args) {
        ProxyFactory proxyFactor = new ProxyFactory(IFuck.class);
        IFuck fuck = proxyFactor.getProxyObject();
        fuck.sayFuck();
    }
}
