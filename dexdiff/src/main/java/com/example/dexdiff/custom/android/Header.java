package com.example.dexdiff.custom.android;


import com.example.dexdiff.custom.util.BufferUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Header {

    //固定112个字节
    public static final int SIZE_OF_HEADER = 112;


    public int stringIdsSize;
    public int stringidsOff;
    public int typeIdsSize;
    public final int typeIdsOff;
    public final int protoIdsSize;
    public final int protoIdsOff;
    public final int fieldIdsSize;
    public final int fieldIdsOff;
    public final int methodIdsSize;
    public final int methodIdsOff;
    public final int classDefsSize;
    public final int classDefsOff;
    public final int dataSize;
    public final int dataOff;
    public int mapOff;
    public int fileSize;

    public Header(ByteBuffer data) {
        byte[] magic = BufferUtil.readBytes(data, 8); //魔数：文件格式、版本
        int checksum = data.getInt();   //校验码
        byte[] signature = BufferUtil.readBytes(data, 20); //签名
        fileSize = data.getInt();
        int headerSize = data.getInt(); //一定是112
        int endianTag = data.getInt(); //一定是 0x12345678

        int linkSize = data.getInt();
        int linkOff = data.getInt();

        //mapList部分偏移
        mapOff = data.getInt();
        stringIdsSize = data.getInt();
        stringidsOff = data.getInt();
        typeIdsSize = data.getInt();
        typeIdsOff = data.getInt();
        protoIdsSize = data.getInt();
        protoIdsOff = data.getInt();
        fieldIdsSize = data.getInt();
        fieldIdsOff = data.getInt();
        methodIdsSize = data.getInt();
        methodIdsOff = data.getInt();
        classDefsSize = data.getInt();
        classDefsOff = data.getInt();
        dataSize = data.getInt();
        dataOff = data.getInt();
    }

    public static Header readFrom(ByteBuffer in) {
        ByteBuffer sectionData = in.duplicate();
        sectionData.order(ByteOrder.LITTLE_ENDIAN);
        sectionData.position(0);
        sectionData.limit(SIZE_OF_HEADER);
        return new Header(sectionData);
    }
}
