package com.gallant.dubbo.spi.spi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * jdk 动态代理
 * 在动态代理类中，在被代理的方法前后各加了一段输出逻辑，而不必破坏原方法。
 */
public class ProxyFactory implements InvocationHandler {
    private Object target;


    public Object getProxyObject(Object target){
        this.target=target;
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("调用前");
        System.out.println(method);
        Object obj=  method.invoke(target,args);
        System.out.println("调用后");
        System.out.println("进行编码");
        System.out.println("发送网络请求");
        System.out.println("将网络请求结果进行解码并返回");
        return obj;
    }
}
