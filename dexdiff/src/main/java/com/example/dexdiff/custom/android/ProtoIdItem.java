package com.example.dexdiff.custom.android;


import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtoIdItem {
    public int shorty_idx;
    public int return_type_idx;
    public int parameters_off;

    public String shorty_str;
    public String return_type_str;

    //参数个数
    public int size;
    public List<Parameter> parameters = new ArrayList<>(size);

    public static class Parameter {

        public short type_idx;
        public String type;

        @Override
        public String toString() {
            return "Parameter{" +
                    "type_idx=" + type_idx +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    public ProtoIdItem(int shorty_idx, int return_type_idx, int parameters_off) {
        this.shorty_idx = shorty_idx;
        this.return_type_idx = return_type_idx;
        this.parameters_off = parameters_off;
    }

    public static Map<Integer, ProtoIdItem> readFrom(ByteBuffer in, int size, int off) throws UTFDataFormatException {
        ByteBuffer sectionData = in.duplicate();
        sectionData.order(ByteOrder.LITTLE_ENDIAN);
        sectionData.position(off);
        Map<Integer, ProtoIdItem> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            //字符串索引 方法声明： void x()=V,void x(int)=VI; void x(String)=VL
            int shorty_idx = sectionData.getInt();
            //类型 type索引 返回值类型
            int return_type_idx = sectionData.getInt();
            // 参数数据在dex的偏移  0为无参数
            int parameters_off = sectionData.getInt();
            ProtoIdItem protoIdItem = new ProtoIdItem(shorty_idx, return_type_idx, parameters_off);
            map.put(i, protoIdItem);
        }

        return map;
    }

    @Override
    public String toString() {
        return "ProtoIdItem{" +
                "shorty_idx=" + shorty_idx +
                ", return_type_idx=" + return_type_idx +
                ", parameters_off=" + parameters_off +
                ", shorty_str='" + shorty_str + '\'' +
                ", return_type_str='" + return_type_str + '\'' +
                ", size=" + size +
                ", parameters=" + parameters +
                '}';
    }
}
