package com.example.basics.designpattern.my.proxy;

/**
 * 海外代理 卖出代理人
 */
public class SaleAgent implements Sale {

    Sale purchase;

    @Override
    public void sell(int price) {
        sellBefore(price);
        int money = price - 1;
        purchase.sell(money);
        sellAfter(money);
    }

    public void sellBefore(int price) {
        System.out.println("代理人拿到钱: " + price + "  , 代理先黑 一块 钱 剩下给代购 。" + (price - 1));
    }

    public void sellAfter(int price) {
        System.out.println("代购卖一个电脑，电脑价格 50 剩下：" + (price - 50) + "  代理吃一块钱的回扣，" + (price - 50 - 1));
    }

    public void setPurchase(Sale purchase) {
        this.purchase = purchase;
    }
}
