package com.example.hotfix.note.hilt.httpprocessor;

import android.app.Application;

import com.example.httpprocessor_hilt.annotation.BindXUtils;

import java.util.ConcurrentModificationException;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {

    @BindXUtils
    @Inject
    IHttpProcessor iHttpProcessor;


    public IHttpProcessor getiHttpProcessor(){
        return iHttpProcessor;
    }

}
