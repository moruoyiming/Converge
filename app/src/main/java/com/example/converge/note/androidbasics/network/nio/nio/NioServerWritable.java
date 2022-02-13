package com.example.converge.note.androidbasics.network.nio.nio;

import static com.example.converge.note.androidbasics.network.nio.Const.DEFAULT_PORT;

/**
 * 类说明：nio通信服务端
 */
public class NioServerWritable {
    private static NioServerHandleWriteable nioServerHandle;

    public static void start(){

    }
    public static void main(String[] args){
        nioServerHandle = new NioServerHandleWriteable(DEFAULT_PORT);
        new Thread(nioServerHandle,"Server").start();
    }

}
