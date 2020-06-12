package com.cocos.aop.util;

import android.app.Application;

import java.lang.reflect.InvocationTargetException;

public class ApplicationUtil {

    private static Application mApplication;

    public static Application getApplication() {
        if (mApplication != null) {
            return mApplication;
        } else {
            mApplication = getApplicationByReflect();
        }
        return mApplication;
    }


    private static Application getApplicationByReflect() {
        try {
            Class<?> clazz = Class.forName("android.app.ActivityThread");
            Object currentActivityThread = clazz.getMethod("currentActivityThread").invoke(null);
            Object application = clazz.getMethod("getApplication").invoke(currentActivityThread);
            return (Application) application;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("application 为空");
    }
}
