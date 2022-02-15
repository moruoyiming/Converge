package com.algorithm.demo.designpattern.create.fatory.abstractFactory;

import com.algorithm.demo.designpattern.entity.Bag;
import com.algorithm.demo.designpattern.entity.Fruit;

/**
 * 抽象水果工厂
 */
public abstract class AbstractFactory {

    public abstract  Fruit getFruit();

    public abstract Bag getBag();

}
