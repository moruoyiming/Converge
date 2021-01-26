package com.example.hotfix.note.thread2;

public class DaemonThreadText {
    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemonThread=new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        Thread.sleep(10000);
    }

    public static class DaemonThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                System.out.println("守护线程 " + i);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
