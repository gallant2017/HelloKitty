package com.gallant.thread;


import com.gallant.common.Print;

import java.util.concurrent.locks.LockSupport;

/**
 * Thread.r1.suspend(); 堵塞线程时极其容易发生死锁,而且jstack监控其线程还处于Runnable状态,无法排查问题
 * 引用了LockSupport实现
 *
 *
 * Created by huangjunhao on 20/2/18.
 */
public class LockSupportDemo implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread r1=new Thread(new LockSupportDemo());
        r1.start();
        r1.suspend(); //已经被废弃
        Thread.sleep(30000);
        r1.resume();
        Print.Info("complete.");

        LockSupport.unpark(r1);

    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.park();
        for(;;){

        }
    }
}
