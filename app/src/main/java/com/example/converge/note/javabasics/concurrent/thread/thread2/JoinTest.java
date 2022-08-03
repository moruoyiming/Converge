package com.example.converge.note.javabasics.concurrent.thread.thread2;

/**
 * 顺序执行线程
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        BuJingYunThread buJingYunThread1 = new BuJingYunThread("步惊云");
        NieFengThread joinThread2 = new NieFengThread("聂风");
        buJingYunThread1.start();
//        buJingYunThread1.join();//main 线程放弃cpu 控制权 执行线程1 之后 ，main线程才有执行的机会。
        joinThread2.start();

    }

    public static class BuJingYunThread extends Thread {
        private String name;

        public BuJingYunThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                System.out.println(name + "    " + i);
            }
        }
    }

    public static class NieFengThread extends Thread{
        private String name;

        public NieFengThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                System.out.println(name + "    " + i);
            }
        }
    }


}
