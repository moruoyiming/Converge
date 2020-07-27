package com.example.basics.designpattern.create.fatory.abstractFactory;

import com.example.basics.designpattern.entity.Bag;
import com.example.basics.designpattern.entity.Fruit;
import com.example.basics.designpattern.entity.bag.BananaBag;
import com.example.basics.designpattern.entity.fruit.Banana;

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
