package com.gallant.guava;

import com.gallant.common.Print;
import com.google.common.util.concurrent.*;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Guava：google封装的包，扩展了好多线程池，异步调用的方法
 *
 * @Author: Huang Junhao
 * @Date: 2019/12/30 13:49
 */
public class GuavaFutureDemo {

    static class Step1 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            //进行一项精密的计算，特别费时间
            Thread.sleep(5000);
            //终于计算出牛逼的数字
            return 1024;
        }
    }

    static class Step2 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int i = new Random().nextInt(5);
            Thread.sleep(i * 1000);
            return i;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //byJDK();
        byGuava();
    }

    //原生JDK，FutureTask获取结果为阻塞
    public static void byJDK() throws ExecutionException, InterruptedException {
        long base = System.currentTimeMillis();
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new Step1());
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new Step2());
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.submit(futureTask1);
        exec.submit(futureTask2);

        //Get()结果时进入阻塞等待,使用google的封装的Guava可以实现异步非阻塞调用
        Integer result1 = futureTask1.get();
        Integer result2 = futureTask2.get();
        Print.Info("step1:" + result1);
        Print.Info("step2:" + result2);
        Print.Info("总耗时：" + (System.currentTimeMillis() - base));
        exec.shutdown();
    }

    //异步非阻塞，不需要等待Step1，Step2的结果，可以做些其他事情
    public static void byGuava() {
        //Guava
        long base = System.currentTimeMillis();
        ExecutorService exec = Executors.newFixedThreadPool(2);
        ListeningExecutorService guavaExec = MoreExecutors.listeningDecorator(exec);
        ListenableFuture<Integer> futureTask1 = guavaExec.submit(new Step1());
        ListenableFuture<Integer> futureTask2 = guavaExec.submit(new Step2());

        //Step1 回调方法
        Futures.addCallback(futureTask1, new FutureCallback<Integer>() {
            public void onSuccess(Integer val) {
                X.result1 = val.intValue();
            }
            public void onFailure(Throwable throwable) {
                X.result1 = -2;
            }
        });

        //Step1 回调方法
        Futures.addCallback(futureTask2, new FutureCallback<Integer>() {
            public void onSuccess(Integer val) {
                X.result2 = val.intValue();
            }
            public void onFailure(Throwable throwable) {
                X.result2 = -2;
            }
        });
        //可以干点别的事情
        while (X.result1 == -1 || X.result2 == -1) {
            Print.Info("do something else...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Print.Info("step1:" + X.result1);
        Print.Info("step2:" + X.result2);
        Print.Info("总耗时：" + (System.currentTimeMillis() - base));
        exec.shutdown();
    }

    static public class X {
        static public int result1 = -1;
        static public int result2 = -1;
    }
}
