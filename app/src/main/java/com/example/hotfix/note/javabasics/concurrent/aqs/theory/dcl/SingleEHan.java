package com.example.hotfix.note.javabasics.concurrent.aqs.theory.dcl;

/**
 * 饿汉式
 * 枚举
 */
public class SingleEHan {
    private SingleEHan(){}
    private static SingleEHan singleDcl = new SingleEHan();

}
