package com.example.dexdiff.custom.android;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class FieldIdItem {
    public short class_idx;
    public short type_idx;
    public int name_idx;

    public String class_str;
    public String type_str;
    public String name_str;

    public FieldIdItem(short class_idx, short type_idx, int name_idx) {
        this.class_idx = class_idx;
        this.type_idx = type_idx;
        this.name_idx = name_idx;
    }

    public static Map<Integer, FieldIdItem> readFrom(ByteBuffer in, int size, int off) {
        ByteBuffer sectionData = in.duplicate();
        sectionData.order(ByteOrder.LITTLE_ENDIAN);
        sectionData.position(off);
        Map<Integer, FieldIdItem> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            // System.out => PrintStream
            short class_idx = sectionData.getShort(); //所属类 type索引：System
            short type_idx = sectionData.getShort(); //类型 type索引：PrintStream
            int name_idx = sectionData.getInt(); // 属性名 string索引：out
            FieldIdItem fieldIdItem = new FieldIdItem(class_idx, type_idx, name_idx);
            map.put(i, fieldIdItem);
        }
        return map;
    }

    @Override
    public String toString() {
        return "FieldIdItem{" +
                "class_idx=" + class_idx +
                ", type_idx=" + type_idx +
                ", name_idx=" + name_idx +
                ", class_str='" + class_str + '\'' +
                ", type_str='" + type_str + '\'' +
                ", name_str='" + name_str + '\'' +
                '}';
    }
}
