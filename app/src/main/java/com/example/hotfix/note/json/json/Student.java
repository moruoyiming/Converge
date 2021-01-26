package com.example.hotfix.note.json.json;


import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String sax;
    private Integer age;

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    private List<Course> courses = new ArrayList<>();


    public Student() {
    }

    /**
     * 需要我们手动创建的构造函数
     *
     * @param name
     * @param sax
     * @param age
     */
    public Student(String name, String sax, Integer age) {
        this.name = name;
        this.sax = sax;
        this.age = age;
        courses = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSax() {
        return sax;
    }

    public void setSax(String sax) {
        this.sax = sax;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sax='" + sax + '\'' +
                ", age=" + age +
                ", courses=" + courses +
                '}';
    }

    static String CurPath = System.getProperty("user.dir");

    public static void main(String ... args){
            //TODO:
        Student student = new Student();
        student.setName("Zero");
        student.setSax("男");
        student.setAge(28);
        student.addCourse(new Course("英语",78.3f));

        Gson gson = new Gson();
        System.out.println(gson.toJson(student));


        File jsontest = new File(CurPath+"/com/zero/serializabledemo/json/jsontest.json");
        System.out.println(jsontest.getAbsoluteFile());

    }
}
