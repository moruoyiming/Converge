package com.example.converge.note.javabasics.concurrent.thread.threadlocal;

public class TreadCommunicationDemo {
    public static void main(String[] args) {
        Res res = new Res();
        ProduceRunnable produceRunnable = new ProduceRunnable(res);
        ConsumeRunnable consumeRunnable = new ConsumeRunnable(res);
        new Thread(produceRunnable).start();
        new Thread(consumeRunnable).start();

    }

}

/**
 * 描述生产者任务
 */
class ProduceRunnable implements Runnable {
    Res res;

    public ProduceRunnable(Res res) {
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
class ConsumeRunnable implements Runnable {
    Res res;

    public ConsumeRunnable(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        res.out();
    }
}


// 容器 资源 描述
class Res {
    private String name;
    private int id;

    public void put(String name) {
        System.out.println("生产 面包" + name + "   id " + id);
    }

    //消费 取出
    public void out() {
        id -= 1;
        System.out.println("消费 面包" + name + "   id " + id);
    }
}