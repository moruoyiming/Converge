package com.example.hotfix.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ShareReflectUtils {

    public static Field findField(Object instance, String name) throws NoSuchFieldException {
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
        throw new NoSuchFieldException("no such field exception");
    }

    public static Method findMethod(Object instance, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Class clas = instance.getClass();
        while (clas != null) {
            try {
                Method method = clas.getDeclaredMethod(name, parameterTypes);
                if (method != null) {
                    //设置访问权限
                    method.setAccessible(true);
                    return method;
                }
            } catch (NoSuchMethodException e) {
                clas = clas.getSuperclass();
            }
        }
        throw new NoSuchMethodException("no such method exception");

    }

    /**
     * getGenericSuperclass 可以得到包含原始类型，参数化类型，数组，类型变量，基本数据类型
     * @param object
     * @return
     */
    public Class<?> analysisClassInfo(Object object) {
        Type getType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) getType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

}
