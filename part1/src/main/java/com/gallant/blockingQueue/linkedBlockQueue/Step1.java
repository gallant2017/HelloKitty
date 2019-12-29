package com.gallant.blockingQueue.linkedBlockQueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 通过队列实现并行流水线 - 模拟流水线第一步
 * <p>
 * Created by huangjunhao on 19/12/29.
 */
public class Step1 implements Runnable {
    public static BlockingQueue<Msg> step1BQ = new LinkedBlockingDeque<Msg>();
    public static boolean stop = false;
    @Override
    public void run() {
        while (!stop) {
            try {
                //队列中取出
                Msg msg = step1BQ.take();
                msg.j = msg.i + msg.j;
                Step2.step2BQ.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
