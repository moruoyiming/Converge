package com.algorithm.demo.designpattern.create.fatory.fatoryMethod;

import com.algorithm.demo.designpattern.entity.Fruit;
import com.algorithm.demo.designpattern.entity.fruit.Orange;

/**
 * 工厂方法模式
 */
public class OrangeFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Orange("Peter",80);
    }
}
