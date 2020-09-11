package com.example.dexdiff.custom.android;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class MethodIdItem {
    public short class_idx;
    public short proto_idx;
    public int name_idx;

    public MethodIdItem(short class_idx, short proto_idx, int name_idx) {
        this.class_idx = class_idx;
        this.proto_idx = proto_idx;
        this.name_idx = name_idx;
    }

    public static Map<Integer, MethodIdItem> readFrom(ByteBuffer in, int size, int off) {
        ByteBuffer sectionData = in.duplicate();
        sectionData.order(ByteOrder.LITTLE_ENDIAN);
        sectionData.position(off);
        Map<Integer, MethodIdItem> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            //类型 type 索引  方法所在的类
            short class_idx = sectionData.getShort();
            // proto 方法信息 索引
            short proto_idx = sectionData.getShort();
            //字符串索引  方法名
            int name_idx = sectionData.getInt();

            map.put(i, new MethodIdItem(class_idx, proto_idx, name_idx));

        }
        return map;
    }
}
