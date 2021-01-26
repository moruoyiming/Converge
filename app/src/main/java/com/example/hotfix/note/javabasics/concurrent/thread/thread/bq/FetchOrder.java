package com.example.hotfix.note.javabasics.concurrent.thread.thread.bq;

import java.util.concurrent.DelayQueue;

/**
 *类说明：取出到期的订单的功能
 */
public class FetchOrder implements Runnable {
	private DelayQueue<ItemVo<Order>> queue;
	
    public FetchOrder(DelayQueue<ItemVo<Order>> queue){
        this.queue = queue;
    }

	@Override
	public void run() {
		while(true) {
			try {
				ItemVo<Order> item = queue.take();
				Order order = (Order)item.getData();
				System.out.println("Get From Queue:"+"data="
				+order.getOrderNo()+";"+order.getOrderMoney());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}	
}
