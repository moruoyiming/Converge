package com.algorithm.demo.designpattern.create.fatory.abstractFactory;

import com.algorithm.demo.designpattern.entity.Bag;
import com.algorithm.demo.designpattern.entity.Fruit;
import com.algorithm.demo.designpattern.entity.bag.BananaBag;
import com.algorithm.demo.designpattern.entity.fruit.Banana;

/**
 * 水果工厂
 */
public class BananaFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Banana();
    }

    @Override
    public Bag getBag() {
        return new BananaBag();
    }
}
