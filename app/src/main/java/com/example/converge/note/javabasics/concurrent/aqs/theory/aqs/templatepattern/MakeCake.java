package com.example.converge.note.javabasics.concurrent.aqs.theory.aqs.templatepattern;

/**
 * 类说明：生产蛋糕
 */
public class MakeCake {
    public static void main(String[] args) {
        AbstractCake cake = new CheeseCake();
        AbstractCake cake2 = new CreamCake();
        //AbstractCake cake = new MouseCake();
        cake.run();

        AbstractCake smalCake = new SmallCake();
        smalCake.run();

    }
}
