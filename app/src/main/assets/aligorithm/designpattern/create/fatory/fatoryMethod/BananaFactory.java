package com.algorithm.demo.designpattern.create.fatory.fatoryMethod;

import com.algorithm.demo.designpattern.entity.fruit.Banana;
import com.algorithm.demo.designpattern.entity.Fruit;

/**
 * 工厂方法模式
 */
public class BananaFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Banana();
    }
}
