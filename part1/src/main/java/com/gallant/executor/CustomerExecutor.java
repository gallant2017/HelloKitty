package com.gallant.executor;

import com.gallant.common.Print;
import java.util.concurrent.*;

/**
 * 扩展 ThreadPoolExecutor
 * 可以抛出堆栈信息
 * Created by huangjunhao on 19/12/28.
 */
public class CustomerExecutor extends ThreadPoolExecutor {

    public CustomerExecutor(int corePoolSize, int maximumPoolSize,
                            long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command, clientTrack(), Thread.currentThread().getName()));
    }

    private Exception clientTrack() {
        return new Exception("Client strack trace");
    }

    private Runnable wrap(Runnable task, Exception clientStrack, String clientThreadName) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        };
    }

    public static void main(String[] args) {
        int a = 100, b = 0;
        Runnable r1 = () -> {
            Print.Info("a\\b:" + (a / b));
        };
        ExecutorService exec = new CustomerExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10));

        //exec.submit(r1);  //无异常堆栈信息
        exec.execute(r1); //有异常堆栈输出
        exec.shutdown();
    }
}
