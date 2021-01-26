package com.example.converge.note.javabasics.jvm.ex2.leak;

/**
 * @author  King老师
 * 手写一个栈
 */
public class Stack {

    public Object[] elements;//数组来保存
    private int size =0;
    private static final int Cap = 200000;

    public Stack() {
        elements = new Object[Cap];
    }

    public void push(Object e){ //入栈
        elements[size] = e;
        size++;
    }
    public Object pop(){  //出栈
    	size = size -1;
        Object o = elements[size];
        //elements[size] = null;  //让GC 回收掉（这里不写就会内存泄漏）
        return o;
    }
}
