package com.example.hotfix;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hotfix.fix(this,"/sdcard/patch.jar");
    }
}
