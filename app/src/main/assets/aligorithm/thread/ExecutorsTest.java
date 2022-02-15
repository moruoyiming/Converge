package com.algorithm.demo.thread;


import java.util.concurrent.*;

public class ExecutorsTest {

    /**
     * corePoolSize：线程池的核心线程数；
     * <p>
     * maximumPoolSize：线程池的最大线程数；
     * <p>
     * keepAliveTime：线程池空闲时线程的存活时长；
     * <p>
     * unit：线程存活时长大单位，结合上个参数使用；
     * <p>
     * workQueue：存放任务的队列，使用的是阻塞队列；
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService ex = new ThreadPoolExecutor(3, 5,
                5000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                        super.rejectedExecution(r, e);
                        System.out.println("rejected Execution");
                    }
                }
        )
//        {
//            @Override
//            protected void beforeExecute(Thread t, Runnable r) {
//                super.beforeExecute(t, r);
//                System.out.println("beforeExecute");
//            }
//
//            @Override
//            protected void afterExecute(Runnable r, Throwable t) {
//                super.afterExecute(r, t);
//                System.out.println("afterExecute");
//            }
//
//            @Override
//            protected void terminated() {
//                super.terminated();
//                System.out.println("terminated");
//            }
//        }
        ;


        for (int i = 0; i < 5; i++) {
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Runnable execute");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            ex.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
        Task task = new Task();
        Future future = ex.submit(task);
//        shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
        ex.shutdown();
//        shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
//        ex.shutdownNow();

//        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(12);
//        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//        ExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(12);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行任务");
        try {
            System.out.println("task运行结果"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程执行任务完毕");
    }

    static class Task implements Callable<Integer> {

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
