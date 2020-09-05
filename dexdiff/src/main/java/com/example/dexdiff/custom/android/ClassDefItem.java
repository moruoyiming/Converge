package com.example.dexdiff.custom.android;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class ClassDefItem {
    int class_idx;
    int access_flag;
    int superclass_idx;
    //实现了哪些接口，指向DexTypeList结构的偏移  0为无接口
    int interfaces_off;
    //类所在源文件名 字符串索引
    int source_file_idx;
    //注解
    int annotations_idx;
    //类数据偏移
    int class_data_off;
    //静态数据偏移
    int static_value_off;

    public ClassDefItem(int class_idx, int access_flag, int superclass_idx, int interfaces_off, int source_file_idx, int annotations_idx, int class_data_off, int static_value_off) {
        this.class_idx = class_idx;
        this.access_flag = access_flag;
        this.superclass_idx = superclass_idx;
        this.interfaces_off = interfaces_off;
        this.source_file_idx = source_file_idx;
        this.annotations_idx = annotations_idx;
        this.class_data_off = class_data_off;
        this.static_value_off = static_value_off;
    }

    public static Map<Integer, ClassDefItem> readFrom(ByteBuffer in, int size, int off) {
        ByteBuffer sectionData = in.duplicate();
        sectionData.order(ByteOrder.LITTLE_ENDIAN);
        sectionData.position(off);
        Map<Integer, ClassDefItem> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            //类索引 type
            int class_idx = sectionData.getInt();
            //类作用域  Opcodes.ACC_PUBLIC
//            ACC_PUBLIC	0x0001	public
//            ACC_PRIVATE	0x0002	private
//            ACC_PROTECTED	0x0004	protected
//            ACC_STATIC	0x0008	static
//            ACC_FINAL	0x0010	final
            int access_flag = sectionData.getInt();
            //父类的类型  type索引
            int superclass_idx = sectionData.getInt();
            //实现了哪些接口，指向DexTypeList结构的偏移  0为无接口
            int interfaces_off = sectionData.getInt();
            //类所在源文件名 字符串索引
            int source_file_idx = sectionData.getInt();
            //注解
            int annotations_idx = sectionData.getInt();
            //类数据偏移
            int class_data_off = sectionData.getInt();
            //静态数据偏移
            int static_value_off = sectionData.getInt();
            map.put(i, new ClassDefItem(class_idx, access_flag, superclass_idx, interfaces_off, source_file_idx, annotations_idx, class_data_off, static_value_off));
        }

        return map;
    }
}
