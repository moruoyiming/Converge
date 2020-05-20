package com.example.hotfix;

import java.lang.reflect.Field;

public class ShareReflectUtils {

    public static Field findFiled(Object instance, String name) {
        Class clas = instance.getClass();
        while (clas != null) {
            try {
                Field field = clas.getDeclaredField(name);
                if (field != null) {
                    //设置访问权限
                    field.setAccessible(true);
                    return field;
                }
            } catch (NoSuchFieldException e) {
                clas = clas.getSuperclass();
            }
        }

    }

    public static void findMethod(Object instance, String name) {

    }
}
