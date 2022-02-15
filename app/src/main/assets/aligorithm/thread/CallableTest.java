package com.algorithm.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {

    public static void main(String[] args) {

        try {
            Callable<Integer> worker = new Worker();
            FutureTask<Integer> futuretask= new FutureTask<>(worker);
            new Thread(futuretask).start();
            Thread.sleep(1000);
            System.out.println("主线程阻塞，等待子线程执行完毕，结果="+  futuretask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Worker implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行运算");
            Thread.sleep(5000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
