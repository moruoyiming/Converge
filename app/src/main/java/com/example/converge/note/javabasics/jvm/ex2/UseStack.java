package com.example.converge.note.javabasics.jvm.ex2;

/**
 * @author King老师
 * 内存泄漏--案例
 */
public class UseStack {
    static  Stack stack = new Stack();  //new一个栈
    public static void main(String[] args) throws Exception {

        for(int i=0;i<100000;i++){//10万的数据入栈
            stack.push(new String[1*1000]); //入栈
        }
        for(int i=0;i<100000;i++){//10万的数据出栈
            Object o1= stack.pop(); //出栈
        }
        Thread.sleep(Integer.MAX_VALUE);

    }


}
