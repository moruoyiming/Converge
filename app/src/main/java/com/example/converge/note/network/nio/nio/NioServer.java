package com.example.converge.note.network.nio.nio;


import static com.example.converge.note.network.nio.Const.DEFAULT_PORT;

/**
 * 类说明：nio通信服务端
 */
public class NioServer {
    private static NioServerHandle nioServerHandle;

    public static void main(String[] args){
        nioServerHandle = new NioServerHandle(DEFAULT_PORT);
        new Thread(nioServerHandle,"Server").start();
    }

}
