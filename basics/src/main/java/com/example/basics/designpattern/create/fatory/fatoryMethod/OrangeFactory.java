package com.example.basics.designpattern.create.fatory.fatoryMethod;

import com.example.basics.designpattern.entity.Fruit;
import com.example.basics.designpattern.entity.fruit.Orange;

/**
 * 工厂方法模式
 */
public class OrangeFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Orange("Peter",80);
    }
}
