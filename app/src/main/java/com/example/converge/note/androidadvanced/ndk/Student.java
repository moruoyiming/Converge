package com.example.converge.note.androidadvanced.ndk;

import android.util.Log;

public class Student {

    private final static String TAG = Student.class.getSimpleName();

    public String name;

    public int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        Log.i(TAG,"Java set Name:"+name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        Log.i(TAG,"Java set setAge:"+age);
        this.age = age;
    }

    public static void showInfo(String info){
        Log.i(TAG,"Java showInfo:"+info);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
