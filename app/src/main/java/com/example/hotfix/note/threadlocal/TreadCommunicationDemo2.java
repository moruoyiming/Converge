package com.example.hotfix.note.threadlocal;

public class TreadCommunicationDemo2 {
    public static void main(String[] args) {
        Res2 res = new Res2();
        ProduceRunnable2 produceRunnable = new ProduceRunnable2(res);
        ConsumeRunnable2 consumeRunnable = new ConsumeRunnable2(res);
        new Thread(produceRunnable).start();
        new Thread(consumeRunnable).start();

    }

}

/**
 * 描述生产者任务
 */
class ProduceRunnable2 implements Runnable {
    Res2 res;

    public ProduceRunnable2(Res2 res) {
        this.res = res;
    }

    @Override
    public void run() {
        res.put("生产一个面包");
    }
}

/**
 * 描述消费者任务
 */
class ConsumeRunnable2 implements Runnable {
    Res2 res;

    public ConsumeRunnable2(Res2 res) {
        this.res = res;
    }

    @Override
    public void run() {
        res.out();
    }
}


// 容器 资源 描述
class Res2 {
    private String name;
    private int id;

    public synchronized void put(String name) {
        id += 1;
        this.name = name + " 生产 " + id;
        System.out.println("生产 面包" + "   id " + id);
    }

    //消费 取出
    public synchronized void out() {
        System.out.println("消费 面包" + "   id " + id);
        id -= 1;
    }
}