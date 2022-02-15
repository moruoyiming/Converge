package com.algorithm.demo.designpattern.entity;

import com.algorithm.demo.designpattern.action.visit.Visit;

/**
 * 水果接口
 */
public interface Fruit {

    int price();

    void draw();

    int accept(Visit visit);

}
