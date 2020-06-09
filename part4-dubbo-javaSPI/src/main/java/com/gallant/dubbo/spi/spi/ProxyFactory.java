package com.gallant.dubbo.spi.spi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory implements InvocationHandler {
    private Class interfaceClass;
    public ProxyFactory(Class interfaceClass){
        this.interfaceClass=interfaceClass;
    }

    public <T> T getProxyObject(){
        return  (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{interfaceClass},
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method);
        System.out.println("进行编码");
        System.out.println("发送网络请求");
        System.out.println("将网络请求结果进行解码并返回");
        return null;
    }
}
