package com.gallant.reentrantLock;


import com.gallant.common.Print;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock 重入锁
 *
 * lock 获取锁 (可以多次获得,但也需要多次释放,否则其它线程将无法进入临界区)
 * unlock 释放锁
 * Created by huangjunhao on 19/12/26.
 */
public class LockDemo {

    static ReentrantLock lock = new ReentrantLock();
    static int i = 0;

    public static class R1 implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) {
                lock.lock();
                lock.lock();
                i++;
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        R1 r1 = new R1();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Print.Info("i=" + i);
    }
}
