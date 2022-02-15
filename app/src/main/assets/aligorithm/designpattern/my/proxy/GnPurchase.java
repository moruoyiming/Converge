package com.algorithm.demo.designpattern.my.proxy;

/**
 * 国内代购
 */
public class GnPurchase implements Purchase {
    @Override
    public void buy(int price) {
        System.out.println("国内代购，只负责购买，拿到手的钱 " + price);
    }

}
