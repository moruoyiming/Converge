package com.cocos.socket.common.interfaces.common_interfacies.server;


import com.cocos.socket.core.iocore.interfaces.ISendable;
import com.cocos.socket.core.pojo.OriginalData;

public interface IClientIOCallback {

    void onClientRead(OriginalData originalData, IClient client, IClientPool<IClient, String> clientPool);

    void onClientWrite(ISendable sendable, IClient client, IClientPool<IClient, String> clientPool);

}
