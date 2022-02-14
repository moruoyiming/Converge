package com.example.converge.note.androidbasics.network.nio.nio;


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
