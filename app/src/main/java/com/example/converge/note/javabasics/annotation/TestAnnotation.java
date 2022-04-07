package com.example.converge.note.javabasics.annotation;

/**
 * @Date: 2022/4/6
 * @Time: 11:00
 * @Author: Jian
 */
@Lance("params")
public class TestAnnotation {

    //    @Lance("params") 编译出错，Lance注解只能作用在类上
    @LanceMethod(value = "canshu",name = "jianruilin")
    public void method() {

    }

}
