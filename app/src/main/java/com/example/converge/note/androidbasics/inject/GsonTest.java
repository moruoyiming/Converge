package com.example.converge.note.androidbasics.inject;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Date: 2022/4/8
 * @Time: 16:40
 * @Author: Jian
 */
public class GsonTest {

    static class Response<T> {
        T data;
        int code;
        String message;

        public Response(T data, int code, String message) {
            this.data = data;
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "data=" + data +
                    ", code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    static class TypeReference<T> {
        Type type;

        public TypeReference() {
            //获得泛型类型
            Type genericSuperclass = getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //因为泛型可以定义多个A<T,E> 所以是个数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            type = actualTypeArguments[0];
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }


    /**
     * Exception in thread "main" java.lang.ClassCastException: com.google.gson.internal.LinkedTreeMap cannot be cast to com.example.converge.note.androidbasics.inject.Data
     * at com.example.converge.note.androidbasics.inject.Test.main(Test.java:41)
     *
     * @param args
     */
    public static void main(String[] args) {
        Response<Data> dataResponse = new Response<>(new Data("sdfa"), 3, "#2");
        Gson gson = new Gson();
        String json = gson.toJson(dataResponse);
        System.out.println(json);
        //反序列化
//        Response<Data> response = gson.fromJson(json,Response.class);
//        System.out.println(response.data.getClass());
//        {}表示为内部类
        Type type = new TypeToken<Response<Data>>() {
        }.getType();
        //加{}表示成匿名内部类，不加{}表示的是对象。
        //不加{}会报错，TypeRefrence<>会有泛型擦出操作，导致字节码中找不到Response相关签名信息。
        //加{} 字节码中会创建匿名内部类
        //不加{} 字节码中会创建对象
        //Exception in thread "main" java.lang.ClassCastException: java.lang.Class cannot be cast to java.lang.reflect.ParameterizedType
        //	at com.example.converge.note.androidbasics.inject.Test$TypeRefrence.<init>(Test.java:43)
        //	at com.example.converge.note.androidbasics.inject.Test.main(Test.java:75)
        Type type2 = new TypeReference<Response<Data>>(){}.getType();
        System.out.println("type "+type);
        System.out.println("type2 "+type2);
        Response<Data> response1 = gson.fromJson(json, type);
        System.out.println(response1.data.getClass());
    }

}
