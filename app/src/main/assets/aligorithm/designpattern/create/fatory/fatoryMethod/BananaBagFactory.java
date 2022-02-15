package com.algorithm.demo.designpattern.create.fatory.fatoryMethod;

import com.algorithm.demo.designpattern.entity.Bag;
import com.algorithm.demo.designpattern.entity.bag.BananaBag;

/**
 * 工厂方法模式
 */
public class BananaBagFactory implements BagFactory{
    public Bag getBag(){
        return new BananaBag();
    }
}
