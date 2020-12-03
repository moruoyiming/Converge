package com.example.hotfix.utils;

import android.content.Intent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtils {

    public static void hookAMS() {
        try {
            //获取hook点singleton对象
            Class<?> clazz = Class.forName("android.app.ActivityManager");
            Field singletonField = clazz.getDeclaredField("IActivityManagerSingleton");
            singletonField.setAccessible(true);
            Object singleton = singletonField.get(null);

            //获取mInstance对象，method.invoke(mInstance, args) 保证原有流程
            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            final Object mInstance = mInstanceField.get(singleton);

            //获取IActivityManager对象
            Class<?> iActivityManager = Class.forName("android.app.IActivityManager");
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
                            //保证原有执行流程
                            return method.invoke(mInstance, args);
                        }
                    });
            //ActivityManager.getService() 替换系统mInstance对象为代理对象
            mInstanceField.set(singleton, proxyInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void hookHandler() {

    }

}
