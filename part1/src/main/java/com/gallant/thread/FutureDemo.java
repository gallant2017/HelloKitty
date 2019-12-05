package com.gallant.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by huangjunhao on 19/12/5.
 */
public class FutureDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("东东日常, " + Thread.currentThread().getName());

        Callable<Integer> cal1 = new 脱裤子();
        FutureTask<Integer> future1 = new FutureTask<Integer>(cal1);
        Thread thread1=new Thread(future1,"xxx");

        Callable<Boolean> cal2 = new 舌吻();
        FutureTask<Boolean> future2 = new FutureTask<Boolean>(cal2);
        Thread thread2=new Thread(future2,"oooo");

        thread1.start();
        thread2.start();


        try {
            System.out.println( future1.get());
            System.out.println( future2.get());

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("干... ");
        System.out.println("嘿嘿嘿...");
    }

    static class 脱裤子 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            try {
                System.out.println("脱牛仔短裙");
                System.out.println("撕下打底裤");
                System.out.println("......");
                Thread.sleep(5000);
                System.out.println("拔下内裤");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 5;
        }
    }
    static class 舌吻 implements Callable<Boolean>{

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("舌吻");
                System.out.println("吸吮");
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

}
