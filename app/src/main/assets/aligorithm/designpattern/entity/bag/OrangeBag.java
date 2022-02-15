package com.algorithm.demo.designpattern.entity.bag;

import com.algorithm.demo.designpattern.entity.Bag;

/**
 * 桔子包装
 * Created by Peter on 10/9 009.
 */
public class OrangeBag implements Bag {
    @Override
    public void pack() {
        System.out.print("--桔子使用网兜包装");
    }
}
