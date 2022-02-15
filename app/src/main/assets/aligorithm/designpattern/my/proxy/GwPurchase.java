package com.algorithm.demo.designpattern.my.proxy;

/**
 * 国外代购,不光能买，也能卖
 */
public class GwPurchase implements Purchase ,Purchase2{
    @Override
    public void buy(int price) {
        System.out.println("国外代购，购买拿到手的钱 " + price);
    }


    @Override
    public void sell(int price) {
        System.out.println("国外代购，也负责卖货，卖的价格 " + price);
    }
}
