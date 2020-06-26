package com.example.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectManage {

    public static void inJect(Activity activity) {
        injectLayout(activity);
        injectView(activity);
        injectEvent(activity);
    }


    /**
     * 依赖注入 layoutId布局
     * 获取 Activity 上的注解
     *
     * @param activity
     */
    private static void injectLayout(Activity activity) {
        if (activity == null) {
            throw new NullPointerException();
        }
//        1.获取Activity的class对象
        Class<? extends Activity> clazz = activity.getClass();
//        2.判断当前Activity是否为InjectLayout修饰
        if (clazz.isAnnotationPresent(InjectLayout.class)) {
//        3.获取InjectLayout注解
            InjectLayout annotation = clazz.getAnnotation(InjectLayout.class);
            if (annotation != null) {
                try {
//                    4.通过反射获取 当前Activity 的 setContentView 方法
                    Method method = clazz.getMethod("setContentView", int.class);
//                    5.获取注解上的value layoutId
                    int resourceId = annotation.value();
//                    6.调用setContentView
                    method.invoke(activity, resourceId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 依赖注入 获取View
     *
     * @param activity
     */
    private static void injectView(Activity activity) {
        if (activity == null) {
            throw new NullPointerException();
        }
//       1.获取Activity的class对象
        Class<? extends Activity> clazz = activity.getClass();
//       2.判断当前Activity中所有的属性字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
//           3. 判断字段是否为InjectView修饰
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                if (injectView != null) {
                    try {
//                       4.反射获取findViewById方法
                        Method method = clazz.getMethod("findViewById", int.class);
//                       5.获取资源id
                        int resourceId = injectView.value();
//                       6.获取View对象
                        View view = (View) method.invoke(activity, resourceId);
//                       7.设置访问权限
                        field.setAccessible(true);
//                       8.将反射获取到的view赋值到Activity上
                        field.set(activity, view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 依赖注入 获取 OnClick OnLongClick注解事件
     * 注解 + 反射 + 动态代理
     *
     * @param activity
     */
    private static void injectEvent(Activity activity) {
        if (activity == null) {
            throw new NullPointerException();
        }
        try {
//          1.获取Activity的class对象
            Class<? extends Activity> clazz = activity.getClass();
//          2.获取Activity的所有成员方法 排除继承方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
//                3.是否是自定义注解修饰
                if (method.isAnnotationPresent(OnClick.class)) {//TODO OnLongClick事件处理
                    OnClick onClick = method.getAnnotation(OnClick.class);
                    int[] value = onClick.value();
//                    4.获取注解上的注解 元注解
                    InjectEvent injectEvent = onClick.annotationType().getAnnotation(InjectEvent.class);
                    String listenerSetter = injectEvent.listenerSetter();
                    Class<?> listenerType = injectEvent.listenerType();
                    String methodName = injectEvent.methodName();
//                    5.动态代理 生成代理的listener
                    ProxyHandler handler=new ProxyHandler(activity);
                    Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),
                            new Class[]{listenerType}, handler);
                    handler.mapMethod(methodName,method);
//                    6.反射获取 findViewById方法的Method对象
                    Method findViewByIdMethod = clazz.getMethod("findViewById", int.class);
                    findViewByIdMethod.setAccessible(true);
                    for (int id : value) {
//                    7.通过findViewById获取view
                        View btn = (View) findViewByIdMethod.invoke(activity, id);
//                    8.根据listenerSetter方法名和listenerType方法参数找到method
                        Method listenerSetMethod = btn.getClass().getMethod(listenerSetter, listenerType);
                        listenerSetMethod.setAccessible(true);
                        listenerSetMethod.invoke(btn, listener);
                    }
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
