package com.example.converge.note.javabasics.concurrent.thread.thread2;

public class EndThread01 {
    public static void main(String[] args) throws InterruptedException {
        UserThread userThread = new UserThread();
        userThread.start();

        Thread.sleep(10);
        userThread.interrupt();
    }

    public static class UserThread extends Thread {
        @Override
        public void run() {
            super.run();
            String name = currentThread().getName();
            while (true) {
                System.out.println(name + "     =====  is  run");
            }
        }
    }
}
