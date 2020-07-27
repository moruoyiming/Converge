package com.example.basics.designpattern.structure.decorator;

import com.example.basics.designpattern.entity.Bag;

public class BagDecorator implements Bag {
    private Bag bag;  //维持一个对抽象构件对象的引用

    public BagDecorator(Bag bag)  //注入一个抽象构件类型的对象
    {
        this.bag = bag;
    }

    public void pack() {
        bag.pack();
    }
}
