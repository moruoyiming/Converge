package com.example.hotfix.note.javabasics.gson.gson;


import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;
    private String name;
    private String sax;
    private Integer age;
    private List<Course> courses;


    public Student() {
        courses = new ArrayList<>();
    }

    public Student(int id, String name, String sax, Integer age) {
        this.id = id;
        this.name = name;
        this.sax = sax;
        this.age = age;
        courses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addAllCourses(List<Course> courses){
        this.courses.addAll(courses);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sax='" + sax + '\'' +
                ", age=" + age +
                ", courses=" + courses +
                '}';
    }

    public static void main(String... args){
            //TODO:
    }


}
