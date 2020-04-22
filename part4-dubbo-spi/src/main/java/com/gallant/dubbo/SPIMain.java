package com.gallant.dubbo;

import com.sun.tools.javac.util.ServiceLoader;

/**
 * Created by huangjunhao on 20/4/22.
 */
public class SPIMain {

    public static void main(String[] args) {
        ServiceLoader<IFuck> loader = ServiceLoader.load(IFuck.class);
        if (loader != null) {
            loader.forEach(p -> p.sayFuck());
        }
    }
}
