package com.gallant.executor;

import com.gallant.common.Print;

import java.util.concurrent.*;

/**
 * 线程池拒绝策略
 * ThreadPoolExecutor.AbortPolicy 抛出异常防止系统继续执行 RejectedExecutionException
 * ThreadPoolExecutor.CallerRunsPolicy 不会丢弃,只要线程池不关闭,继续执行
 * ThreadPoolExecutor.DiscardOldestPolicy 丢弃最老的一个请求,尝试再次提交当前任务
 * ThreadPoolExecutor.DiscardPolicy 不做任何处理,丢弃
 * Created by huangjunhao on 19/12/28.
 */
public class RejectDemo {

    public static class MyTask implements Runnable {

        @Override
        public void run() {
            Print.Info("搬砖干活...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        ExecutorService exec = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                //自定义拒绝策略
                (Runnable r, ThreadPoolExecutor executor) -> {
                    System.out.println(r.toString() + " 已丢弃");
                }
                //new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 1000; i++) {
            exec.submit(myTask);
        }
        exec.shutdown();
//        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
