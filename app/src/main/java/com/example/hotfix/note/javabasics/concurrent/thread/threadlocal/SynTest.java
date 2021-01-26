package com.example.hotfix.note.javabasics.concurrent.thread.threadlocal;

// 多线程执行，存在安全性问题
// 加锁解决
public class SynTest {
    private int count = 0;

    /**
     * synchronized 对象锁 持有的锁 是SynTest.this
     * 没有静态static
     */
    public synchronized void incCount() {
        count++;
    }

    public void incCount2() {
        synchronized (SynTest.this) {
            count++;
        }
    }

    public Object object=new Object();

    public void incCount3(){
        synchronized (object) {
            count++;
        }
    }


    public static void main(String[] args) {
        SynTest synTest = new SynTest();
        Count count = new Count(synTest);
        Count count2 = new Count(synTest);
        count.start();
        count2.start();
    }

    public static class Count extends Thread {

        private SynTest synTest;

        public Count(SynTest synTest) {
            this.synTest = synTest;
        }

        @Override
        public void run() {

            for (int i = 0; i < 10000; i++) {
                synTest.incCount();
                System.out.println(synTest.count);
            }
        }
    }
}
