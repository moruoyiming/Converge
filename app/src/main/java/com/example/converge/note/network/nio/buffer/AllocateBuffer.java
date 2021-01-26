package com.example.converge.note.network.nio.buffer;

/**
 * 类说明：Buffer的分配 -Xmx100M -Xms100M
 */
public class AllocateBuffer {
//    public static void main(String[] args) {
//
//        OperatingSystemMXBean osmxb = (OperatingSystemMXBean)
//                ManagementFactory.getOperatingSystemMXBean();
//
//
//        System.out.println("----------Test allocate--------");
//        System.out.println("before allocate:" + osmxb.getFreePhysicalMemorySize());
//
//        /*堆上分配*/
//        ByteBuffer buffer = ByteBuffer.allocate(200000);
//        System.out.println("buffer = " + buffer);
//        System.out.println("after allocate:" + osmxb.getFreePhysicalMemorySize());
//
//        /* 这部分用的直接内存*/
//        ByteBuffer directBuffer = ByteBuffer.allocateDirect(200000);
//        System.out.println("directBuffer = " + directBuffer);
//        System.out.println("after direct allocate:" + osmxb.getFreePhysicalMemorySize());
//
//        System.out.println("----------Test wrap--------");
//        byte[] bytes = new byte[32];
//        buffer = ByteBuffer.wrap(bytes);
//        System.out.println(buffer);
//
//        buffer = ByteBuffer.wrap(bytes, 10, 10);
//        System.out.println(buffer);
//    }
}
