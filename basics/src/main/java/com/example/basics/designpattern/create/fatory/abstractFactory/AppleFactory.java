package com.example.basics.designpattern.create.fatory.abstractFactory;

import com.example.basics.designpattern.entity.Bag;
import com.example.basics.designpattern.entity.Fruit;
import com.example.basics.designpattern.entity.bag.AppleBag;
import com.example.basics.designpattern.entity.fruit.Apple;

/**
 * 水果工厂
 */
public class AppleFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Apple();
    }

    @Override
    public Bag getBag() {
        return new AppleBag();
    }
}
