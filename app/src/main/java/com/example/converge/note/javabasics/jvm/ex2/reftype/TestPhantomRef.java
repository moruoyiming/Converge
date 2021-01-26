package com.example.converge.note.javabasics.jvm.ex2.reftype;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class TestPhantomRef {
    public static void main(String[] args) {
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());
    }
}
