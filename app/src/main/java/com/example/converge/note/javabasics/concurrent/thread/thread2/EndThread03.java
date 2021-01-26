package com.example.converge.note.javabasics.concurrent.thread.thread2;

public class EndThread03 {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new UserThread();
        Thread userThread = new Thread(runnable);
        userThread.start();

        Thread.sleep(10);
        userThread.interrupt();
    }

    public static class UserThread implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(name + "     =====  is  run");
            }
        }
    }
}
