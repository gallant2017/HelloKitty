package com.gallant.blockingQueue.linkedBlockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 通过队列实现并行流水线 - 模拟流水线第二步
 * Created by huangjunhao on 19/12/29.
 */
public class Step2 implements Runnable {

    public static BlockingQueue<Msg> step2BQ = new LinkedBlockingQueue<Msg>();
    public static boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            try {
                Msg msg = step2BQ.take();
                msg.i = msg.i * msg.j;
                Step3.step3BQ.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
