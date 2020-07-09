package com.example.hotfix.note.class06.bq;

import java.util.concurrent.DelayQueue;

/**
 *类说明：将订单推入队列
 */
public class PutOrder implements Runnable {
    private DelayQueue<ItemVo<Order>> queue;

    public PutOrder(DelayQueue<ItemVo<Order>> queue){
        this.queue = queue;
    }

	@Override
	public void run() {
		//5秒后到期
		Order orderTb = new Order("Tb12345",366);
		ItemVo<Order> itemTb = new ItemVo<Order>(5,orderTb);
		queue.offer(itemTb);
		System.out.println("订单5秒后超时："+orderTb.getOrderNo()+";"
                +orderTb.getOrderMoney());
		//8秒后到期
		Order orderJd = new Order("Jd54321",366);
		ItemVo<Order> itemJd = new ItemVo<Order>(8,orderJd);
		queue.offer(itemJd);
		System.out.println("订单8秒后超时："+orderJd.getOrderNo()+";"
                +orderJd.getOrderMoney());

	}	
}
