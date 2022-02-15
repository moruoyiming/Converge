package com.algorithm.demo.designpattern.create.fatory.abstractFactory;

import com.algorithm.demo.designpattern.entity.Bag;
import com.algorithm.demo.designpattern.entity.Fruit;
import com.algorithm.demo.designpattern.entity.bag.OrangeBag;
import com.algorithm.demo.designpattern.entity.fruit.Orange;

/**
 * 水果工厂
 */
public class OrangeFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Orange("Peter",50);
    }

    @Override
    public Bag getBag() {
        return new OrangeBag();
    }
}
