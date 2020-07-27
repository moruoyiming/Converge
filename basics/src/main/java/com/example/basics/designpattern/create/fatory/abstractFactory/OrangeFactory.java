package com.example.basics.designpattern.create.fatory.abstractFactory;

import com.example.basics.designpattern.entity.Bag;
import com.example.basics.designpattern.entity.Fruit;
import com.example.basics.designpattern.entity.bag.OrangeBag;
import com.example.basics.designpattern.entity.fruit.Orange;

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
