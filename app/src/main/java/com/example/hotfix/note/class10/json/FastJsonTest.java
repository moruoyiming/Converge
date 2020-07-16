//package com.example.hotfix.note.class10.json;
//
//import com.alibaba.fastjson.JSONObject;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//
//public class FastJsonTest {
//
//    static String CurPath = System.getProperty("user.dir");
//    public static void main(String ... args) throws Exception {
//            //TODO:
//        Student student = new Student();
//        student.setName("FastJson");
//        student.setSax("男");
//        student.setAge(28);
//        student.addCourse(new Course("英语", 78.3f));
//        student.addCourse(new Course("语文", 88.9f));
//        student.addCourse(new Course("数学", 48.2f));
//
//        //1. 生成json文件
//        File file = new File(CurPath + "/fastjsontest.json");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        JSONObject.writeJSONString(fileOutputStream,student);
//
//        //2. 反序列化
//        Student student1 = JSONObject.parseObject(new FileInputStream(file),Student.class);
//        System.out.println(student1);
//    }
//}
