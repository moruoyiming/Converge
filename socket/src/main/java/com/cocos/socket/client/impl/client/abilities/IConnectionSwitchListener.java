package com.cocos.socket.client.impl.client.abilities;


import com.cocos.socket.client.sdk.client.ConnectionInfo;
import com.cocos.socket.client.sdk.client.connection.IConnectionManager;

/**
 * Created by xuhao on 2017/6/30.
 */

public interface IConnectionSwitchListener {
    void onSwitchConnectionInfo(IConnectionManager manager, ConnectionInfo oldInfo, ConnectionInfo newInfo);
}
