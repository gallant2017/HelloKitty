package com.gallant.thread;

import com.gallant.common.Print;

/**
 *
 * wait() 会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态
 * notify() 通知某个正在等待这个对象的控制权的线程可以继续运行。
 * notifyAll() 通知所有等待这个对象控制权的线程继续运行
 * @Author: Huang Junhao
 * @Date: 2019/12/26 15:27
 */
public class WaitNotifyDemo extends Thread {
    static Object obj=new Object();

    public String action;
    @Override
    public void run() {
        Print.Info("start..");
        try {
            while (!isInterrupted()) {
                if(action=="w") {
                    synchronized (obj) {
                        Print.Info("干活，");
                        Thread.sleep(3000);
                        Print.Info("最重要的活干完了，让出控制权");
                        obj.wait();
                        Print.Info("after wait.");
                        interrupt();
                    }
                }
                else if(action=="n") {
                    synchronized (obj) {
                        Print.Info("该我上了...");
                        Thread.sleep(2000);
                        obj.notifyAll();
                        Print.Info("after notify.");
                        interrupt();
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyDemo t1=new WaitNotifyDemo();
        WaitNotifyDemo t2=new WaitNotifyDemo();
        t1.setName("T1");
        t1.action="w";
        t2.setName("T2");
        t2.action="n";
        t1.start();
        Thread.sleep(100);
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
