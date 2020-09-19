package com.cocos.socket.core.iocore.interfaces;

import com.cocos.socket.core.protocol.IReaderProtocol;

import java.nio.ByteOrder;

public interface IIOCoreOptions {

    ByteOrder getReadByteOrder();

    int getMaxReadDataMB();

    IReaderProtocol getReaderProtocol();

    ByteOrder getWriteByteOrder();

    int getReadPackageBytes();

    int getWritePackageBytes();

    boolean isDebug();

}
