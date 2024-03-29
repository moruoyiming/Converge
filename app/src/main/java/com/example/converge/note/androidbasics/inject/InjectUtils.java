package com.example.converge.note.androidbasics.inject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 注解&反射
 */
public class InjectUtils {

    /**
     * 注解反射动态完成findViewById操作
     *
     * @param activity
     */
    public static void injectView(Activity activity) {
        Class<? extends Activity> clz = activity.getClass();
        //获得当前类所有成员
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            //判断属性是否被InjectView 注解声明
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                //获得注解中设置的id
                int id = injectView.value();
                View view = activity.findViewById(id);
                //反射设置属性的值  需要设置访问权限setAccessible 允许操作private的属性
                field.setAccessible(true);
                //判断字段是否为 static 修饰符修饰
                field.getModifiers();
                try {
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注解反射动态完成获取Intent参数获取参数操作
     *
     * @param activity
     */
    public static void InjectExtras(Activity activity) {
        Class<? extends Activity> clz = activity.getClass();
        Intent intent = activity.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                field.setAccessible(true);
                String key = TextUtils.isEmpty(autowired.value()) ? field.getName() : autowired.value();
                if (bundle.containsKey(key)) {
                    Object obj = bundle.get(key);
                    // todo Parcelable数组类型不能直接设置，其他的都可以.
                    //获得数组单个元素类型
                    Class<?> componentType = field.getType().getComponentType();
                    //field.getType().isArray()  判断是否为数组
                    //判断字段是否实现接口
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        //如果命中则说明是 Parcelable 数组
                        Object[] objects = (Object[]) obj;
                        //创建对应类型的数组并由objects拷贝
                        //(Class<? extends Object[]>) field.getType() 为 Parcelable[].class
                        Object[] objs = Arrays.copyOf(objects, objects.length, (Class<? extends Object[]>) field.getType());
                        obj = objs;
                    }
                    try {
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
