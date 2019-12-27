package com.gallant.executor;

import com.gallant.common.Print;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量
 * 允许多个线程同时访问
 * <p>
 * Created by huangjunhao on 19/12/27.
 */
public class SemaphoreDemo implements Runnable {

    //允许5个线程同时访问
    final Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try {
            semp.acquire();
            Print.Info("获得信号量锁");
            Thread.sleep(2000);
            semp.release();
            Print.Info("释放信号量锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        SemaphoreDemo obj = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(obj);
        }
    }
}
