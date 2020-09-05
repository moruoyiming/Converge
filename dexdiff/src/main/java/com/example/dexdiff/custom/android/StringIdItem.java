package com.example.dexdiff.custom.android;


import com.example.dexdiff.custom.util.BufferUtil;

import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class StringIdItem implements Comparable<StringIdItem> {
    public int string_data_off;
    public int utf16_size;
    public String data;


    public StringIdItem(int size, String data) {
        this.utf16_size = size;
        this.data = data;
    }

    public StringIdItem(int off, int size, String data) {
        this.string_data_off = off;
        this.utf16_size = size;
        this.data = data;
    }

    @Override
    public int compareTo(StringIdItem s) {
        return data.compareTo(s.data);
    }

    public static Map<Integer, StringIdItem> readFrom(ByteBuffer in, int size, int off) throws UTFDataFormatException {
        ByteBuffer sectionData = in.duplicate();
        sectionData.order(ByteOrder.LITTLE_ENDIAN);
        sectionData.position(off);

        Map<Integer, StringIdItem> map = new HashMap<>();
        for (int i = 0; i < size; i++) {

            //字符串数据内容偏移
            int string_data_off = sectionData.getInt();
            int position = sectionData.position();
            sectionData.position(string_data_off);
            //字符串数据长度
            int utf16_size = BufferUtil.readUnsignedLeb128(sectionData);
            //字符串数据
            String data = BufferUtil.readMutf8(sectionData, utf16_size);
            sectionData.position(position);

            StringIdItem stringItem = new StringIdItem(string_data_off, utf16_size, data);
            map.put(i, stringItem);
        }
        return map;
    }

}
