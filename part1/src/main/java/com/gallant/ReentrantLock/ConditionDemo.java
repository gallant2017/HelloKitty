package com.gallant.ReentrantLock;

import com.gallant.common.Print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Condition 和 ReentrantLock 配合使用 (相当于 Synchronized 和 wait)
 * condition.await();  -> obj.wait()
 * condition.signal()  -> obj.notify()
 * condition.signalAll()  -> obj.notifyAll()
 *
 * Created by huangjunhao on 19/12/27.
 */
public class ConditionDemo {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static class R implements Runnable {

        @Override
        public void run() {
            if (lock.tryLock()) {
                Print.Info("获取锁....");
                try {
                    Thread.sleep(3000);
                    Print.Info("干完了重要的活");
                    condition.await();
                    Print.Info("收工.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    Print.Info("退出线程");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new R());
        t1.start();
        Thread.sleep(100);
        lock.lock();
        Print.Info("获得锁...");
        condition.signal();
        lock.unlock();
        t1.join();
        Print.Info("退出线程");
    }
}
