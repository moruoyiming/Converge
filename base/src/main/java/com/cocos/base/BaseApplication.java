package com.cocos.base;

import android.app.Application;

public class BaseApplication extends Application {

    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
