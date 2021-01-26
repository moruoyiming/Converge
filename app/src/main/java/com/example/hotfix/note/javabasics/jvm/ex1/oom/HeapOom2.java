package com.example.hotfix.note.javabasics.jvm.ex1.oom;

import java.util.LinkedList;
import java.util.List;


/**
 * @author King老师
 * VM Args：-Xms30m -Xmx30m -XX:+PrintGC    堆的大小30M
 * 造成一个堆内存溢出(分析下JVM的分代收集)
 * GC调优---生产服务器推荐开启(默认是关闭的)
 * -XX:+HeapDumpOnOutOfMemoryErro
 */
public class HeapOom2 {
   public static void main(String[] args)
   {
        //GC ROOTS
       List<Object> list = new LinkedList<>(); // list   当前虚拟机栈（局部变量表）中引用的对象  是1，不是走2
       int i =0;
       while(true){
           i++;
           if(i%10000==0) System.out.println("i="+i);
           list.add(new Object());
       }

   }
}
