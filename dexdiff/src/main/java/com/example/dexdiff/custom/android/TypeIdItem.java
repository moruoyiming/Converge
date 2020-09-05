package com.example.dexdiff.custom.android;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class TypeIdItem {
    public int desctriptor_idx;
    public String data;

    public TypeIdItem(int desctriptor_idx, String data) {
        this.desctriptor_idx = desctriptor_idx;
        this.data = data;
    }

    public static Map<Integer, TypeIdItem> readFrom(ByteBuffer in, int size, int off) {
        ByteBuffer sectionData = in.duplicate();
        sectionData.order(ByteOrder.LITTLE_ENDIAN);
        sectionData.position(off);

        Map<Integer, TypeIdItem> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            //字符串索引 类名
            int desctriptor_idx = sectionData.getInt();
            map.put(i, new TypeIdItem(desctriptor_idx, null));
        }
        return map;
    }
}
