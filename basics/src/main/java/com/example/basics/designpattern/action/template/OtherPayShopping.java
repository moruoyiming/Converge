package com.example.basics.designpattern.action.template;

import com.example.basics.designpattern.entity.Fruit;

import java.util.List;

/**
 * 模板方法模式
 * 购物车费用结算过程
 */
public class OtherPayShopping extends ShoppingCart{


    public OtherPayShopping(List<Fruit> products) {
        super(products);
    }

    @Override
    protected void pay(int money) {
        System.out.println("代付成功");
    }

}
