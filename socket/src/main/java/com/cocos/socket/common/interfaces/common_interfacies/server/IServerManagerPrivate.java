package com.cocos.socket.common.interfaces.common_interfacies.server;


import com.cocos.socket.core.iocore.interfaces.IIOCoreOptions;


public interface IServerManagerPrivate<E extends IIOCoreOptions> extends IServerManager<E> {
    void initServerPrivate(int serverPort);
}
