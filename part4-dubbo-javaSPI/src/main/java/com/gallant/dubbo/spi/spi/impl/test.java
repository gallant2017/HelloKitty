package com.gallant.dubbo.spi.spi.impl;

import com.gallant.dubbo.spi.spi.FuckYou;
import com.gallant.dubbo.spi.spi.IFuck;
import com.gallant.dubbo.spi.spi.ProxyFactory;

public class test {

    public static void main(String[] args) {
        ProxyFactory proxyFactor = new ProxyFactory();
        IFuck fuck = (IFuck)proxyFactor.getProxyObject(new FuckYou());
        System.out.println(fuck.sayFuck());
    }
}
