package com.example.basics.designpattern.create.fatory.abstractFactory;

import com.example.basics.designpattern.entity.Bag;
import com.example.basics.designpattern.entity.Fruit;

/**
 * 抽象水果工厂
 */
public abstract class AbstractFactory {

    public abstract  Fruit getFruit();

    public abstract Bag getBag();

}
