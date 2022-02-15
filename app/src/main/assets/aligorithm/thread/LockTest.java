package com.algorithm.demo.thread;

/**
 * 当线程A持有独占锁a，并尝试去获取独占锁 b 的同时，线程 B 持有独占锁 b，并尝试获取独占锁a的情况下,
 * 而A和B释放其持有的锁的前提又都是先获得对方持有的另一个锁,因此这两个线程最终都无法获得它们申请的另一个锁,
 * 最终两个线程都处于无限等待的状态,即产生了死锁
 */
//产生死锁的四个必要条件：
//        (1)资源互斥：涉及的资源必须是独占的,即每个资源一次只能够被一个线程使用.若其它线程访问该资源，只能等待，直至占有该资源的线程使用完成后释放该资源；
//        (2)占用并等待资源：线程获得一定的资源之后，又对其它资源发出请求，但是该资源可能被其它线程占有，此时请求阻塞，但又对自己获得的资源保持不放；
//        (3)资源不可剥夺：是指线程已获得的资源，在未完成使用之前，不可被剥夺，只能在使用完后自己释放；
//        (4)循环等待资源：涉及的线程必须在等待别的线程持有的资源,而这些线程又反过来在等待第一个线程所持有的资源,即若干线程之间形成一种头尾相接的循环等待资源关系.
//        这些条件是死锁产生的必要条件而非充分条件,这就是说只要产生了死锁,那么上面这些条件一定同时成立,但是上述条件即使同时成立,也不一定就能产生死锁.

public class LockTest implements Runnable {

    private Object object1 = new Object();
    private Object object2 = new Object();
    private int count = 0;//起一个标志作用

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        lockTest.count = 0;
        new Thread(lockTest).start();
        // 让当前线程睡眠
        Thread.sleep(500);
        // 修改value的值
        lockTest.count = 1;
        new Thread(lockTest).start();
    }

    @Override
    public void run() {
        if (count == 0) {
            synchronized (object1) {
                System.out.println("线程A : 我得到object1 的锁");
                try {
                    // 让当前线程睡眠,从而让另外一个线程可以先得到对象o2的锁
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("线程A : 我还想要得到o2的锁");
                // 在得到o1锁之后,又想得到o2的锁
                synchronized (object2) {
                    System.out.println("线程A : 我得到了o2的锁");
                }
            }
        } else {
            synchronized (object2) {
                System.out.println("线程B : 我得到object2 的锁");
                try {
                    // 让当前线程睡眠,从而让另外一个线程可以先得到对象o1的锁
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("线程B : 我还想要得到o1的锁");
                // 在得到o1锁之后,又想得到o2的锁
                synchronized (object1) {
                    System.out.println("线程B : 我得到了o1的锁");
                }
            }
        }
    }
}
