package com.gallant.dubbo.impl;

import com.gallant.dubbo.IFuck;

/**
 * Created by huangjunhao on 20/4/22.
 */
public class Stop implements IFuck {

    @Override
    public void sayFuck() {
        System.out.println("oh,fuck 雅蠛蝶...");
    }
}
