package com.gallant.thread;

/**
 * 多线程同时写入，临界区变量i会打乱，必须加锁
 *
 * @Author: Huang Junhao
 * @Date: 2019/12/26 14:59
 */
public class SynchronizedDemo extends Thread {

    static volatile int i = 0;
    static Object obj = new Object();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-- runing...");
        for (int j = 0; j < 1000000; j++) {
            synchronized (obj) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new SynchronizedDemo();
        Thread t2 = new SynchronizedDemo();
        t1.setName("T1");
        t2.setName("T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("complete. i=" + i);
    }


}
