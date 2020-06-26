package com.example.basics.thread;

/**
 * 写两个线程，一个线程打印1-52，另一个线程打印字母A-Z。打印 顺序为12A34B56C……5152Z
 */
public class ThreadDemo {
    public static void main(String[] args) {

    }

    class Thread1 extends Thread {
        private Object object;

        void Thread1(Object obj) {
            this.object = obj;
        }

        @Override
        public void run() {
        }
    }
}
