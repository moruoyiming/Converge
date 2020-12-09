package com.example.hotfix.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class HookUtils {
    private static final String TARGET_INTENT = "target_intent";

    public static void hookAMS() {
        try {
            Field singletonField;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { // 小于8.0
                Class<?> clazz = Class.forName("android.app.ActivityManagerNative");
                singletonField = clazz.getDeclaredField("gDefault");
            } else {
                Class<?> clazz = Class.forName("android.app.ActivityManager");
                singletonField = clazz.getDeclaredField("IActivityManagerSingleton");
            }
            singletonField.setAccessible(true);
            Object singleton = singletonField.get(null);
            //获取mInstance对象，method.invoke(mInstance, args) 保证原有流程
            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            final Object mInstance = mInstanceField.get(singleton);

            //获取IActivityManager对象  动态代理替换的对象系统的IActivityManager
            Class<?> iActivityManager = Class.forName("android.app.IActivityManager");
            Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{iActivityManager}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            //当执行的方法是startActivity时做处理
                            /**
                             * IActivityManager类的方法
                             * startActivity(whoThread, who.getBasePackageName(), intent,
                             *                         intent.resolveTypeIfNeeded(who.getContentResolver()),
                             *                         token, target != null ? target.mEmbeddedID : null,
                             *                         requestCode, 0, null, options)
                             */
                            // 过滤
                            if ("startActivity".equals(method.getName())) {
                                int index = -1;
                                for (int i = 0; i < args.length; i++) {
                                    if (args[i] instanceof Intent) {
                                        index = i;
                                        break;
                                    }
                                }
                                //得到原始的Intent 对象 插件的Intent
                                Intent intent = (Intent) args[index];

                                Intent proxyIntent = new Intent();
                                proxyIntent.setComponent(new ComponentName("com.example.hotfix", "com.example.hotfix.note.plugin.ProxyActivity"));
                                proxyIntent.putExtra(TARGET_INTENT, intent);
                                args[index] = proxyIntent;
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
        try {
            //获取 ActivityThread 类的 Class 对象
            Class<?> clazz = Class.forName("android.app.ActivityThread");
            //获取 ActivityThread 对象
            Field activityThreadField = clazz.getDeclaredField("sCurrentActivityThread");
            activityThreadField.setAccessible(true);
            Object activityThread = activityThreadField.get(null);

            //获取 mH 对象
            Field mHField = clazz.getDeclaredField("mH");
            mHField.setAccessible(true);
            final Handler mH = (Handler) mHField.get(activityThread);

            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);

            Handler.Callback callback = new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    //通过msg 可以拿到Intent 可以换回执行插件的Intent
                    //找到 Intent 的方便替换地方 在这个类里边ActivityClientRecord Intent intent非静态
                    //msg.obj == ActivityClientRecord
                    switch (msg.what) {
                        case 100:
                            try {
                                Field intentField = msg.obj.getClass().getDeclaredField("intent");
                                intentField.setAccessible(true);
                                //启动代理Intent
                                Intent proxyIntent = (Intent) intentField.get(msg.obj);
                                //启动插件Intent
                                Intent pluginIntent = proxyIntent.getParcelableExtra(TARGET_INTENT);
                                if (pluginIntent != null) {
                                    intentField.set(msg.obj, pluginIntent);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 159://9.0适配  EXECUTE_TRANSACTION
                            //获取 mActivityCallbacks对象
                            try {
                                Field mActivityCallbacksField = msg.obj.getClass().getDeclaredField("mActivityCallbacks");
                                mActivityCallbacksField.setAccessible(true);
                                List mActivityCallbacks = (List) mActivityCallbacksField.get(msg.obj);
                                for (int i = 0; i < mActivityCallbacks.size(); i++) {
                                    if (mActivityCallbacks.get(i).getClass().getName()
                                            .equals("android.app.servertransaction.LaunchActivityItem")) {
                                        Object launchActivityItem = mActivityCallbacks.get(i);
                                        //获取启动代理的Intent
                                        Field mIntentField = launchActivityItem.getClass().getDeclaredField("mIntent");
                                        mIntentField.setAccessible(true);
                                        Intent proxyIntent = (Intent) mIntentField.get(launchActivityItem);

                                        //目标 intent 替换 proxyIntent
                                        Intent intent = proxyIntent.getParcelableExtra(TARGET_INTENT);
                                        if (intent != null) {
                                            mIntentField.set(launchActivityItem, intent);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    return false;
                }
            };

            mCallbackField.set(mH, callback);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
