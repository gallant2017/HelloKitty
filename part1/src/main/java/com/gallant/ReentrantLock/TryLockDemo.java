package com.gallant.reentrantLock;

import com.gallant.common.Print;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TryLock---可以很好避免死锁,两个重载
 * tryLock(3, TimeUnit.SECONDS):等待指定时间
 * tryLock():无等待
 * Created by huangjunhao on 19/12/27.
 */
public class TryLockDemo {
    static ReentrantLock lock = new ReentrantLock();

    public static class R implements Runnable {

        @Override
        public void run() {
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)) {
                    Print.Info("获取锁..");
                    Thread.sleep(4000);
                } else {
                    Print.Info("获取锁失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        R r1 = new R();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
