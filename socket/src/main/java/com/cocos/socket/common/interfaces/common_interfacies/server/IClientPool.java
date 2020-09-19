package com.cocos.socket.common.interfaces.common_interfacies.server;


import com.cocos.socket.core.iocore.interfaces.ISendable;

public interface IClientPool<T, K> {

    void cache(T t);

    T findByUniqueTag(K key);

    int size();

    void sendToAll(ISendable sendable);
}
