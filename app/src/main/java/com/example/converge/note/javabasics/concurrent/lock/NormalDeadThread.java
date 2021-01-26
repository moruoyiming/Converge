package com.example.converge.note.javabasics.concurrent.lock;

public class NormalDeadThread {

    private static Object no13 = new Object();
    private static Object no14 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread.currentThread().setName("李四");
        StudentThread studentThread=new StudentThread("张三");
        studentThread.start();
        getNo14();
    }

    private static void getNo13() throws InterruptedException {
        String threadName = StudentThread.currentThread().getName();
        synchronized (no13) {
            System.out.println(threadName + " get n013 ");
            Thread.sleep(100);
            synchronized (no14) {
                System.out.println(threadName + " get n014 ");
            }
        }
    }

    private static void getNo14() throws InterruptedException {
        String threadName = StudentThread.currentThread().getName();
        synchronized (no14) {
            System.out.println(threadName + " get n014 ");
            Thread.sleep(100);
            synchronized (no13) {
                System.out.println(threadName + " get n013 ");
            }
        }
    }

    public static class StudentThread extends Thread {

        private String name;

        public StudentThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                getNo13();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
