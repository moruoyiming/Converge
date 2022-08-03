package com.example.converge.note.javabasics.concurrent.thread;

/**
 * Synchronized同步法
 * 1、基本思路
 * 使用同步块和wait、notify的方法控制三个线程的执行次序。具体方法如下：从大的方向上来讲，该问题为三线程间的同步唤醒操作，
 * 主要的目的就是ThreadA->ThreadB->ThreadC->ThreadA循环执行三个线程。为了控制线程执行的顺序，那么就必须要确定唤醒、
 * 等待的顺序，所以每一个线程必须同时持有两个对象锁，才能进行打印操作。一个对象锁是prev，就是前一个线程所对应的对象锁，
 * 其主要作用是保证当前线程一定是在前一个线程操作完成后（即前一个线程释放了其对应的对象锁）才开始执行。还有一个锁就是自身对象锁。
 * 主要的思想就是，为了控制执行的顺序，必须要先持有prev锁（也就前一个线程要释放其自身对象锁），然后当前线程再申请自己对象锁，
 * 两者兼备时打印。之后首先调用self.notify()唤醒下一个等待线程（注意notify不会立即释放对象锁，只有等到同步块代码执行完毕后才会释放），
 * 再调用prev.wait()立即释放prev对象锁，当前线程进入休眠，等待其他线程的notify操作再次唤醒。
 *
 */
public class ABC_Synch {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A",c,a);
        ThreadPrinter pb = new ThreadPrinter("B",a,b);
        ThreadPrinter pc = new ThreadPrinter("C",b,c);

        new Thread(pa).start();
        Thread.sleep(100);
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }

    public static class ThreadPrinter implements Runnable{
        private String name;
        private Object prev;
        private Object self;

        public ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while(count > 0){//多线程并发，不能用if，必须使用while循环
                synchronized (prev){//先获取 prev 锁
                    synchronized (self){// 在获取 self 锁
                        System.out.println(name);// 打印
                        count--;
                        self.notifyAll();// 唤醒其他竞争self锁，注意此时self锁并未立即释放。
                    }
                    //此时执行完self的同步块，这是self锁才释放
                    try {
                        if(count == 0){//如果count==0，表示这是最后一次打印操作，通过notifyAll操作释放对象锁
                            prev.notifyAll();
                        }else{
                            prev.wait();//立即释放prev锁，当前线程休眠，等待唤醒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
