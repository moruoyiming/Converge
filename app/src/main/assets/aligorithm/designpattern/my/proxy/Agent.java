package com.algorithm.demo.designpattern.my.proxy;

/**
 * 代理人
 */
public class Agent implements Purchase {

    //国内代购
    Purchase gnPurchase;


    @Override
    public void buy(int price) {
        buyBefor(price);
        int qian = price - 1;
        gnPurchase.buy(qian);
        buyAfter(qian);
    }

    public void buyBefor(int price) {
        System.out.println("代理人拿到钱: " + price + "  , 代理先黑 一块 钱 剩下给代购 。" + (price - 1));
    }

    public void buyAfter(int price) {
        System.out.println("代购买一个电脑，电脑价格 50 剩下：" + (price - 50)+"  代理吃一块钱的回扣，"+(price-50-1));
    }

    public void setGnPurchase(Purchase gnPurchase) {
        this.gnPurchase = gnPurchase;
    }
}
