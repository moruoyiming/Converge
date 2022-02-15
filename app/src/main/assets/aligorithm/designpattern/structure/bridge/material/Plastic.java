package com.algorithm.demo.designpattern.structure.bridge.material;

/**
 * 包装接口
 * Material
 */
public class Plastic implements Material{
    public void draw(){
        System.out.print("用塑料袋");
    }

}
