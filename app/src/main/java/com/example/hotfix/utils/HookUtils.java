package com.example.hotfix.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtils {

    public static void hookAMS() {
        try {
            Class<?> clazz = Class.forName("android.app.ActivityManager");
            Field singletonField = clazz.getDeclaredField("IActivityManagerSingleton");
            singletonField.setAccessible(true);
            Object singleton = singletonField.get(null);
            Class<?> iActivityManager = Class.forName("android.app.IActivityManager");
            Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iActivityManager}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return method.invoke(singleton, args);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
