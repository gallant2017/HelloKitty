package com.gallant.blockingQueue.linkedBlockQueue;


import com.gallant.common.Print;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * BlockingQueue 线程间共享数据通道就是很好的使用了ReentrantLock的例子
 * take 去除数据时如果队列里没有数据,使用绑定的condition->notEmpty.await(); 释放当前锁资源,当前线程进入等待
 * put 装入数据时会使用绑定的使用绑定的condition->notEmpty.signal() 通知take里的await线程继续执行.,
 * 同理,如果队列满了,则notFull.await(),take操作后会则notFull.signal()
 * <p>
 *
 *     await:当前线程进入等待状态并释放锁
 *     signal:缓存等待中的线程,使得它重新去获取锁,所以signal之后必须要释放锁,lock.unlock(),否则await的线程无法继续运行.
 * <p>
 * Created by huangjunhao on 20/2/18.
 */
public class ArrayBlockingQueueTakeTest implements Runnable {

    int action = 0;

    public ArrayBlockingQueueTakeTest(int action) {
        this.action = action;
    }

    public static BlockingQueue q = new ArrayBlockingQueue(10);

    @Override
    public void run() {
        if (action == 0) {
            Print.Info("before take a object");
            try {
                Object obj = q.take();
                Print.Info("after take object" + String.valueOf(obj));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (action == 1) {
            try {
                q.put("装载了第一个数据");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread r1 = new Thread(new ArrayBlockingQueueTakeTest(0));
        Thread r2 = new Thread(new ArrayBlockingQueueTakeTest(1));
        r1.start();
        Thread.sleep(1000);
        r2.start();
        Print.Info("主线程退出");

    }
}
