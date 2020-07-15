package com.example.hotfix.note.class10.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class TestProtoBuf {

    public static void main(String... args) {
        //TODO:
        //序列化
        byte[] bs = serialize();
        System.out.println(Arrays.toString(bs));

        System.out.println("================反序列化====================");
        //反序列化
        _StudentSerializable._Student student = deserialize(bs);
        System.out.println(student);

    }


    public static byte[] serialize() {
        _StudentSerializable._Course.Builder courseBuild = _StudentSerializable._Course.newBuilder()
                .setName("语文").setScore(66.5f);
        _StudentSerializable._Student.Builder builder = _StudentSerializable._Student.newBuilder();
        builder.setName("Av").setAge(17).setSax("男").addCourses(courseBuild);
        _StudentSerializable._Student student = builder.build();
        return student.toByteArray();
    }

    public static _StudentSerializable._Student deserialize(byte[] bs) {
        try {
            return _StudentSerializable._Student.parseFrom(bs);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }
}
