package com.example.hotfix.note.class05;

public class TreadCommunicationDemo3 {
    public static void main(String[] args) {
        Res3 res = new Res3();
        ProduceRunnable3 produceRunnable = new ProduceRunnable3(res);
        ConsumeRunnable3 consumeRunnable = new ConsumeRunnable3(res);
        new Thread(produceRunnable).start();
        new Thread(consumeRunnable).start();

    }

}

/**
 * 描述生产者任务
 */
class ProduceRunnable3 implements Runnable {
    Res3 res;

    public ProduceRunnable3(Res3 res) {
        this.res = res;
    }

    @Override
    public void run() {
        for (int i=0;i<20 ;i++){
            res.put("生产一个面包");
        }
    }
}

/**
 * 描述消费者任务
 */
class ConsumeRunnable3 implements Runnable {
    Res3 res;

    public ConsumeRunnable3(Res3 res) {
        this.res = res;
    }

    @Override
    public void run() {
        for (int i=0;i<20 ;i++){
            res.out();
        }
    }
}


// 容器 资源 描述
class Res3 {
    private int id;
    private boolean flag;//先生产后消费

    public synchronized void put(String name) {
//        生产之前判断标记
        if (!flag) {
            id += 1;
            System.out.println("生产面包=" + Thread.currentThread().getName() + ",id=" + id);
            //修改标记
            flag=true;
            /**
             * 唤醒wait() 被冻结的线程
             */
            notify();// 使用wait() notify() 需要有锁包裹
            /**
             * 释放cpu 执行权，cpu执行其他线程
             */
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //消费 取出
    public synchronized void out() {
        if (flag) {
            System.out.println("消费面包=" + Thread.currentThread().getName() + ",id=" + id);
            //修改标记
            flag=false;
            /**
             * 唤醒wait() 被冻结的线程
             */
            notify();// 使用wait() notify() 需要有锁包裹
            /**
             * 释放cpu 执行权，cpu执行其他线程
             */
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}