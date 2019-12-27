package com.gallant.executor;

import com.gallant.common.Print;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器
 * 等待所有线程执行完成,主线程才会继续执行,适用于发射火箭场合
 * 等待所有检查工作完成继续
 * end.countDown();
 * end.await();
 * Created by huangjunhao on 19/12/27.
 */
public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch end = new CountDownLatch(5);
    static CountDownLatchDemo demo = new CountDownLatchDemo();
    static final String[] jobs = new String[]{"传感器检测完成!", "GPS定位正常!", "燃料注入完成!", "推进剂注入完成!", "点火系统启动完成!"};
    static int index = jobs.length;
    static synchronized int getJob() {
        return --index;
    }

    @Override
    public void run() {
        try {
            //模拟检查工作时长
            Thread.sleep(new Random().nextInt(10) * 1000);
            Print.Info(jobs[getJob()]);
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Print.Info("神州100号进入检查就绪阶段.");
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.submit(demo);
        }
        try {
            end.await();
            Print.Info("点火!!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }
}
