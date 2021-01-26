package com.example.hotfix.note.javabasics.concurrent.thread.thread2;

public class HasInterruptException {

    public static void main(String[] args) throws InterruptedException {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();
        Thread.sleep(10);
        interruptThread.interrupt();
    }

    public static class InterruptThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("interrupt thread before");
            while (!isInterrupted()) {
                // 可以中断 线程
//                System.out.println(" 线程 中断 ");


                try {
                    System.out.println("interrupt thread run");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {//sleep 会把中断信号清除
                    System.out.println("interrupt thread catch interrrupt flag is " + isInterrupted() + " at ");
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println("interrupt thread name" + currentThread().getName());
            }
            System.out.println("interrupt thread after");

        }
    }
}
