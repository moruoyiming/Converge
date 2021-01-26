package com.example.hotfix.note.javabasics.jvm.ex1;
/**
 * @author King老师
 * 方法内联
 */
public class MethodDeal {

    public static void main(String[] args) {
       // max(1,2);//调用max方法：  虚拟机栈 --入栈（max 栈帧）
        boolean i1 = 1>2;
    }
    public static boolean max(int a,int b){//方法的执行入栈帧。
        return a>b;
    }
}
