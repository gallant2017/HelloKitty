package com.gallant.common;

/**
 * @Author: Huang Junhao
 * @Date: 2019/12/26 16:41
 */
public class Print {
    public static void Info(String msg){
        System.out.println(String.format("[%s-%s]:%s",Thread.currentThread().getName(),
                Thread.currentThread().getId(),msg));
    }
}
