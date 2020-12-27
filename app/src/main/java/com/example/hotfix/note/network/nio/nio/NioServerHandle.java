package com.example.hotfix.note.network.nio.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * 类说明：nio通信服务端处理器
 */
public class NioServerHandle implements Runnable{

    private volatile boolean started;
    //TODO



    /**
     * 构造方法
     * @param port 指定要监听的端口号
     */
    public NioServerHandle(int port) {
        //TODO
    }

    @Override
    public void run() {
        //TODO
    }

    public void stop(){
        started = false;
    }

}
