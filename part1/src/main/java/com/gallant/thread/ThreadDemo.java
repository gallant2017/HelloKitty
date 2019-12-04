package com.gallant.thread;

import java.net.SocketTimeoutException;

/**
 * Threan Join 异步阻塞
 * 线程Join合并流程
 * 使用场景: A线程调用了B线程的join方法,等待B线程执行完成,在B线程没有完成之前,A线程阻塞
 * 使用弊端:被合并的线程没有返回值,只能合并线程,不能取的执行结果
 * Created by huangjunhao on 19/12/3.
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("东东日常, " + Thread.currentThread().getName());
        Thread x1=new 脱裤子();
        Thread x2=new 舌吻();
        x1.start();
        x2.start();

        //合并多线程前戏
        x1.join();
        x2.join();

        System.out.println("干... ");
        System.out.println("嘿嘿嘿...");
    }

    static class 脱裤子 extends Thread {
        public 脱裤子() {
            super(" ** 脱裤子");
        }
        public void run() {

            try {
                System.out.println("脱牛仔短裙");
                System.out.println("撕下打底裤");
                System.out.println("......");
                Thread.sleep(5000);
                System.out.println("拔下内裤");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class 舌吻 extends Thread {
        public 舌吻() {
            super("** 舌吻");
        }
        public void run() {
            try {
                System.out.println("舌吻");
                System.out.println("吸吮");
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
