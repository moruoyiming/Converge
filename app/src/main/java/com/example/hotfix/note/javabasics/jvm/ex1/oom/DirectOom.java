package com.example.hotfix.note.javabasics.jvm.ex1.oom;

import java.nio.ByteBuffer;

/**
 * @author King老师
 * VM Args：-XX:MaxDirectMemorySize=100m
 * 堆外内存（直接内存溢出）
 */
public class DirectOom {
    public static void main(String[] args) {
        //直接分配128M的直接内存(100M)
        ByteBuffer bb = ByteBuffer.allocateDirect(128*1024*1204);
    }
}
