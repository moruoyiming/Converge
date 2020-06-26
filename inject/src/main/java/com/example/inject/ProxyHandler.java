package com.example.inject;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ProxyHandler implements InvocationHandler {

    private WeakReference<Activity> mHandlerRef;

    private HashMap<String, Method> mMethodHashMap;

    public ProxyHandler(Activity activity) {
        mHandlerRef = new WeakReference<>(activity);
        mMethodHashMap = new HashMap<>();
    }

    public void mapMethod(String name, Method method) {
        mMethodHashMap.put(name, method);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object handler = mHandlerRef.get();
            if (null == handler) return null;
            String name = method.getName();
            //将onClick方法的调用映射到activity 中的注解标注的方法
            Method realMethod = mMethodHashMap.get(name);
//            Log.i("injectEvent", "proxy=" + proxy + ",method=" + method.getName() + ",realMethod=" + realMethod);
            if (null != realMethod) {
                return realMethod.invoke(handler, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
