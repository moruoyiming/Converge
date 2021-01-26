package com.example.hotfix.note.lock.bq;

import java.util.concurrent.DelayQueue;

/**
 *类说明：延时队列测试程序
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<ItemVo<Order>> queue = new DelayQueue<ItemVo<Order>>();//延时队列

        new Thread(new PutOrder(queue)).start();
        new Thread(new FetchOrder(queue)).start();

        //每隔500毫秒，打印个数字
        for(int i=1;i<15;i++){
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
