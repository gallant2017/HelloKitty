package com.gallant.blockingQueue.linkedBlockQueue;

import com.gallant.common.Print;

import java.util.concurrent.*;

/**
 * 通过队列实现并行流水线 - 模拟流水线第三步
 * Created by huangjunhao on 19/12/29.
 */
public class Step3 implements Runnable {
    public static BlockingQueue<Msg> step3BQ = new LinkedBlockingQueue<Msg>();
    public static boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            try {
                Msg msg = step3BQ.take();
                msg.i = msg.i / 2;
                Print.Info("装配完成:" + msg.orgStr + msg.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        exec.submit(new Step1());
        exec.submit(new Step2());
        exec.submit(new Step3());
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "((" + i + "+" + j + ")*" + i + ")/2=";
                Step1.step1BQ.add(msg);
            }
        }
    }
}
