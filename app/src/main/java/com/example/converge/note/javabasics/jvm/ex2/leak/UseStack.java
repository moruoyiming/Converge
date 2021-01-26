package com.example.converge.note.javabasics.jvm.ex2.leak;

/**
 * @author King老师
 * 内存泄漏--变量作用域不合理
 */
public class UseStack {

    public static void main(String[] args) {
        Stack stack = new Stack();  //new一个栈
        for(int i=0;i<100000;i++){
            stack.push(Math.random()); //入栈
        }
        for(int i=0;i<100000;i++){
            Object o1= stack.pop(); //出栈
        }
        System.out.println(stack); //打印栈中的数据

    }


}
