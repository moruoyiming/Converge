package com.algorithm.demo.designpattern.create.fatory.fatoryMethod;

import com.algorithm.demo.designpattern.entity.Bag;
import com.algorithm.demo.designpattern.entity.Fruit;

/**
 * 水果店测试
 * Created by Peter on 10/8 008.
 */
public class FruitStoreTest {

    private static FruitFactory fruitFactory;
    private static BagFactory bagFactory;

    public static void main(String[] args) {
        pack();
    }

    /**
     * 邮寄打包
     */
    public static void pack(){
        //初始化苹果工厂
        fruitFactory = new AppleFactory();//猎取工厂不对应
        Fruit fruit = fruitFactory.getFruit();
        fruit.draw();

        //初始化苹果包装工厂
        bagFactory = new BananaBagFactory();
        Bag bag = bagFactory.getBag();

        bag.pack();

        //....邮寄业务
    }


}
