package com.example.hotfix.note.class04;

/**
 * 顺序执行线程
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        JoinThread joinThread1 = new JoinThread("步惊云");
        JoinThread joinThread2 = new JoinThread("聂风");
        joinThread1.start();
        joinThread1.join();//main 线程放弃cpu 控制权 执行线程1 之后 ，main线程才有执行的机会。
        joinThread2.start();

    }

    public static class JoinThread extends Thread {
        private String name;

        public JoinThread(String name) {
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
