package com.example.hotfix.note.class14.ex2;


/**
 *
 * VM参数： -verbose:gc
 */
public class JavaStackSolt {
    public static void main(String[] args) {
        {
            byte[] a= new byte[32*1024*1024];
        }
        //int b=0;  //不要使用得对象应手动赋值为null
        System.gc();
    }
}
