package com.algorithm.demo.designpattern.my.templatemethod;

/**
 * 类说明：奶油蛋糕
 */
public class CreamCake extends AbstractCake {
    @Override
    protected void shape() {
        System.out.println("奶油蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("奶油蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("奶油蛋糕烘焙");
    }
}
