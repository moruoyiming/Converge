package com.example.basics.designpattern.my.proxy;

/**
 * 代理人，代理购买服务
 */
public class Agent implements Purchase {

    Purchase purchase;


    @Override
    public void buy(int price) {
        buyBefore(price);
        int money = price - 1;
        purchase.buy(money);
        buyAfter(money);
    }

    public void buyBefore(int price) {
        System.out.println("代理人拿到钱: " + price + "  , 代理先黑 一块 钱 剩下给代购 。" + (price - 1));
    }

    public void buyAfter(int price) {
        System.out.println("代购买一个电脑，电脑价格 50 剩下：" + (price - 50) + "  代理吃一块钱的回扣，" + (price - 50 - 1));
    }

    public void setGnPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
