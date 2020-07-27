package com.example.basics.designpattern.create.fatory.fatoryMethod;

import com.example.basics.designpattern.entity.Bag;
import com.example.basics.designpattern.entity.bag.BananaBag;

/**
 * 工厂方法模式
 */
public class BananaBagFactory implements BagFactory{
    public Bag getBag(){
        return new BananaBag();
    }
}
