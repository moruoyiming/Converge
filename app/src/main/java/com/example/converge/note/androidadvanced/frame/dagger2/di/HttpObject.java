package com.example.converge.note.androidadvanced.frame.dagger2.di;

import android.util.Log;

import javax.inject.Inject;

/**
 * 1.提供用于注入的对象
 */
public class HttpObject {

    private static final String TAG = "Dagger2";

    private String name;

    @Inject //Dagger2通过@Inject注解可以在需要这个类实例的时候来找到这个构造函数并把相关实例构造出来，以此来为被@Inject标记了的变量提供依赖
    public HttpObject(String name) {
        Log.i(TAG, "HttpObject: " + name);
        this.name = name;
    }

    public String printName(){
        Log.i(TAG, "printName: "+name);
        return name;
    }
}
