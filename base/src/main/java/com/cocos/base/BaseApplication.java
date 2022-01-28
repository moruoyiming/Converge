package com.cocos.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Context getAppContext(){
        return mApplication;
    }
}
