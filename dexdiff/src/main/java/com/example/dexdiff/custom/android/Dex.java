package com.example.dexdiff.custom.android;


import com.example.dexdiff.custom.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

public class Dex {


    public ByteBuffer data;
    public Header header;
    public Map<Integer, StringIdItem> string_ids;
    public Map<Integer, TypeIdItem> type_ids;
    public Map<Integer, ProtoIdItem> proto_ids;
    public Map<Integer, FieldIdItem> field_ids;
    public Map<Integer, MethodIdItem> method_ids;
    public Map<Integer, ClassDefItem> classDefs;

    public Dex(File dexFile) throws IOException {
        byte[] rawData = FileUtil.readFile(dexFile);
        this.data = ByteBuffer.wrap(rawData);
        this.data.order(ByteOrder.LITTLE_ENDIAN);

        //读取header
        header = Header.readFrom(data);


        //字符串数据索引，记录了每个字符串在数据区的偏移量
        string_ids = StringIdItem.readFrom(data, header.stringIdsSize, header.stringidsOff);

        //类型名的字符串索引
        type_ids = TypeIdItem.readFrom(data, header.typeIdsSize, header.typeIdsOff);
        for (TypeIdItem value : type_ids.values()) {
            StringIdItem stringIdItem = string_ids.get(value.desctriptor_idx);
            value.data = stringIdItem.data;
        }

        // 原型数据索引，记录了方法声明的字符串，返回类型字符串，参数列表
        proto_ids = ProtoIdItem.readFrom(data, header.protoIdsSize, header.protoIdsOff);
        for (ProtoIdItem value : proto_ids.values()) {
            StringIdItem stringIdItem = string_ids.get(value.shorty_idx);
            value.shorty_str = stringIdItem.data;

            TypeIdItem typeIdItem = type_ids.get(value.return_type_idx);
            value.return_type_str = typeIdItem.data;

            if (value.parameters_off != 0) {
                int position = data.position();
                data.position(value.parameters_off); //参数数据在dex的偏移
                value.size = data.getInt(); //参数个数
                for (int i = 0; i < value.size; i++) {
                    //参数类型：typeid
                    ProtoIdItem.Parameter parameter = new ProtoIdItem.Parameter();
                    parameter.type_idx = data.getShort(); //类型 type 索引
                    parameter.type = type_ids.get(Integer.valueOf(parameter.type_idx)).data;
                    value.parameters.add(parameter);
                }
                data.position(position);
            }
        }


        //字段数据索引，记录了所属类，类型以及方法名
        field_ids = FieldIdItem.readFrom(data, header.fieldIdsSize, header.fieldIdsOff);
        for (FieldIdItem value : field_ids.values()) {
            value.class_str = type_ids.get(Integer.valueOf(value.class_idx)).data;
            value.type_str = type_ids.get(Integer.valueOf(value.type_idx)).data;
            value.name_str = string_ids.get(value.name_idx).data;
        }

        method_ids = MethodIdItem.readFrom(data, header.methodIdsSize, header.methodIdsOff);

        classDefs = ClassDefItem.readFrom(data, header.classDefsSize, header.classDefsOff);

    }
}
