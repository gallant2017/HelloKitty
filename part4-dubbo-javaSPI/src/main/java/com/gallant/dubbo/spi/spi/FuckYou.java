package com.gallant.dubbo.spi.spi;

public class FuckYou implements IFuck {
    @Override
    public String sayFuck() {
        System.out.println("hey guy, fuck you.");
        return "这是返回值";
    }
}
