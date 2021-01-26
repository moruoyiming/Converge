//package com.example.hotfix.note.class10.json;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class JacksonTest {
//
//    static String CurPath = System.getProperty("user.dir");
//
//    public static void main(String... args) throws Exception {
//        //TODO:
//        Student student = new Student();
//        student.setName("杰克逊");
//        student.setSax("男");
//        student.setAge(28);
//        student.addCourse(new Course("英语", 78.3f));
//        student.addCourse(new Course("语文", 88.9f));
//        student.addCourse(new Course("数学", 48.2f));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        //jackson序列化
//        File file = new File(CurPath + "/jacksontest.json");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        objectMapper.writeValue(fileOutputStream, student);
//
//
//        //反序列化
//        Student student1 = objectMapper.readValue(file, Student.class);
//        System.out.println(student1);
//
////        HashMap<String, Student> studentHashMap = new HashMap<>();
////        Student stu1 = new Student("King", "男", 32);
////        stu1.addCourse(new Course("物理", 68.9f));
////        Student stu2 = new Student("Mark", "男", 33);
////        studentHashMap.put("key1", stu1);
////        studentHashMap.put("key2", stu2);
////        System.out.println("studentHashMap:\n" + studentHashMap);
////        JacksonUtil.encode2File(studentHashMap,CurPath + "/jacksontest1.json");
////        String jsonStr = JacksonUtil.encode(studentHashMap);
////        System.out.println(jsonStr);
//
//        //反序列化 TypeReference用法
////        HashMap<String,Student> studentHashMap1 = objectMapper.readValue(jsonStr,HashMap.class);//错误做法
//        //正确的方式
////        HashMap<String,Student> studentHashMap1 = objectMapper.readValue(jsonStr, new TypeReference<HashMap<String,Student>>(){});
////        System.out.println("studentHashMap1:\n" + studentHashMap1);
//
//
//        //List
////        List<Student> studentList = new ArrayList<>();
////        studentList.add(stu1);
////        studentList.add(stu2);
////        JacksonUtil.encode2File(studentList, CurPath + "/jacksontest2.json");
////        String jsonStr2 = JacksonUtil.encode(studentList);
////
////        List<Student> studentList1 = objectMapper.readValue(jsonStr2, objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Student.class));
////        System.out.println("studentList1:\n" + studentList1);
//
//    }
//}
