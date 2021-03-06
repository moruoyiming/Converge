package com.example.basics.designpattern.entity;

import com.example.basics.designpattern.action.visit.Visit;

/**
 * 水果接口
 */
public interface Fruit {

    int price();

    void draw();

    int accept(Visit visit);

}
