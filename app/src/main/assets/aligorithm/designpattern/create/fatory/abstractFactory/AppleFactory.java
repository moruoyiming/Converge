package com.algorithm.demo.designpattern.create.fatory.abstractFactory;

import com.algorithm.demo.designpattern.entity.Bag;
import com.algorithm.demo.designpattern.entity.Fruit;
import com.algorithm.demo.designpattern.entity.bag.AppleBag;
import com.algorithm.demo.designpattern.entity.fruit.Apple;

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
