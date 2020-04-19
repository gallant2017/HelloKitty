package com.gallant.thread;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

/**
 * 分而治之
 *
 * Created by huangjunhao on 20/2/18.
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
//        int i=101;
//        System.out.println(i/=10);
//        System.out.println(i/=10);
//        System.out.println(i/=10);
//
//        int y=101;
//        System.out.println(y%=10);
//        System.out.println(y%=10);
//        System.out.println(105%10);

//        System.out.println(Math.pow(2,8));
//        System.out.println( Math.max(20,100));
        HashMap<String,String> map=new HashMap<>();
        map.put("test","hahaha");
        map.put("test","222222");
        System.out.println(hash("test"));
        System.out.println(hash("gallant"));
        System.out.println(hash("yuidff"));
        System.out.println(map.get("test"));
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
