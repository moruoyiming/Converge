package com.example.basics.designpattern.create.fatory.fatoryMethod;

import com.example.basics.designpattern.entity.Bag;
import com.example.basics.designpattern.entity.bag.AppleBag;

/**
 * 工厂方法模式
 */
public class AppleBagFactory implements BagFactory{
    public Bag getBag(){
        return new AppleBag();
    }
}
