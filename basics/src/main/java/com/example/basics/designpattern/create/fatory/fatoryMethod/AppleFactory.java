package com.example.basics.designpattern.create.fatory.fatoryMethod;

import com.example.basics.designpattern.entity.fruit.Apple;
import com.example.basics.designpattern.entity.Fruit;

/**
 * 工厂方法模式
 */
public class AppleFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Apple();
    }
}
