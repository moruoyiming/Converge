package com.example.hotfix.note.javabasics.jvm.ex1.oom;


/**
 * @author  King老师
 * VM Args：-Xms30m -Xmx30m -XX:+PrintGCDetails
 * 堆内存溢出（直接溢出）
 */
public class HeapOom {
   public static void main(String[] args)
   {

       String[] strings = new String[35*1000*1000];  //35m的数组（堆）
   }
}
