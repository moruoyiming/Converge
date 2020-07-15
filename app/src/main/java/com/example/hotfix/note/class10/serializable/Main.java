package com.example.hotfix.note.class10.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class Main {

    public static void main(String... args) throws Exception {
        //TODO:
        test01();//
    }


    public static void test01() throws Exception {
        //TODO:
        Student student = new Student("Zero", "男", 18);
        student.addCourse(new Course("语文", 90.2f));
        student.addCourse(new Course("数学", 89.3f));
        //序列化
        byte[] bytes = SerializeableUtils.serialize(student);
        String path = System.getProperty("user.dir") + "\\SerializableDemo\\src\\main\\Demostudent.out";
        SerializeableUtils.saveObject(student, path);
        System.out.println(Arrays.toString(bytes));

        System.out.println("=============反序列化=====================");
        //反序列化
        Student student1 = SerializeableUtils.deserialize(bytes);
        student1.newDate();
        System.out.println("Student: " + student1);

    }

}
