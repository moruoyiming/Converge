package com.example.hotfix.note.frame.dagger2.di;

import android.util.Log;

import javax.inject.Inject;

public class DatabaseObject {
    private static final String TAG = "Dagger2";

    private String name;

    @Inject //Dagger2通过@Inject注解可以在需要这个类实例的时候来找到这个构造函数并把相关实例构造出来，以此来为被@Inject标记了的变量提供依赖
    public DatabaseObject(String name) {
        Log.i(TAG, "DatabaseObject: " + name);
        this.name = name;
    }

    public String getFunction(){
        Log.i(TAG, "DatabaseObject: "+name);
        return name;
    }
}
