package com.example.hotfix.utils;

import android.content.Intent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtils {

    public static void hookAMS() {
        try {
            //获取hook点 singleton对象
            Class<?> clazz = Class.forName("android.app.ActivityManager");
            Field singletonField = clazz.getDeclaredField("IActivityManagerSingleton");
            singletonField.setAccessible(true);
            Object singleton = singletonField.get(null);
            //获取IActivityManager对象
            Class<?> iActivityManager = Class.forName("android.app.IActivityManager");
            Field mInstanceField = iActivityManager.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{iActivityManager}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            //当执行的方法是startActivity时做处理
                            if ("startActivity".equals(method.getName())) {
                                int index = 0;
                                for (int i = 0; i < args.length; i++) {
                                    if (args[i] instanceof Intent) {
                                        index = i;
                                        break;
                                    }
                                }
                                //得到原始的Intent 对象 插件的Intent
                                Intent intent = (Intent) args[index];
                            }

                            return method.invoke(singleton, args);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hookHandler(){

    }

}
