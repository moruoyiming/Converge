package com.example.converge.note.javabasics.jvm.ex1.oom;


/**
 * 栈溢出 -Xss1m
 */
public class StackOverFlow {

    public void king(){//一个栈帧--虚拟机栈运行
        king();//无穷的递归
    }
    public static void main(String[] args)throws Throwable {
        StackOverFlow javaStack = new StackOverFlow(); //new一个对象
        javaStack.king();
    }
}
