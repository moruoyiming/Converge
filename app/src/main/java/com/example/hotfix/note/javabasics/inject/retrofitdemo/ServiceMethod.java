package com.example.hotfix.note.javabasics.inject.retrofitdemo;

import android.util.Log;

import com.example.hotfix.note.javabasics.inject.retrofitdemo.annotation.Field;
import com.example.hotfix.note.javabasics.inject.retrofitdemo.annotation.GET;
import com.example.hotfix.note.javabasics.inject.retrofitdemo.annotation.POST;
import com.example.hotfix.note.javabasics.inject.retrofitdemo.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * 记录请求类型、参数、完整地址
 */
public class ServiceMethod {

    HttpUrl baseUrl;
    Call.Factory callFactory;
    String httpMethod;
    String relativeUrl;
    boolean hasBody;
    ParameterHandler[] parameterHandlers;
    FormBody.Builder formBuilder;
    HttpUrl.Builder urlBuilder;

    public ServiceMethod(Builder builder) {
        baseUrl = builder.mRetrofit.baseUrl;
        callFactory = builder.mRetrofit.callFactory;
        httpMethod = builder.httpMethod;
        relativeUrl = builder.relativeUrl;
        hasBody = builder.hasBody;
        parameterHandlers = builder.parameterHandlers;
        //如果有请求体，创建一个okhttp的请求体对象
        if (hasBody) {
            formBuilder = new FormBody.Builder();
        }
    }

    /**
     * args 接口方法传递的形参
     *
     * @param args
     * @return
     */
    public Object invoke(Object[] args) {
//        1. 处理请求地址及参数
        for (int i = 0; i < parameterHandlers.length; i++) {
            //ParameterHandler 已经保存了key 现在将value 传递
            ParameterHandler parameterHandler = parameterHandlers[i];
            parameterHandler.apply(this, args[i].toString());

        }
        //获取最终的请求地址
        HttpUrl httpUrl;
        if (urlBuilder != null) {
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        httpUrl = urlBuilder.build();

        //请求体
        FormBody formBody = null;
        if (formBuilder != null) {
            formBody = formBuilder.build();
        }
        Request request = new Request.Builder().url(httpUrl).method(httpMethod, formBody).build();
        return callFactory.newCall(request);
    }

    /**
     * GET 请求  把k-v拼到url上
     *
     * @param key
     * @param value
     */
    public void addQueryParameter(String key, String value) {
        if (urlBuilder != null) {
            //urlBuilder 为baseUrl 和 relativeUrl结合体
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        urlBuilder.addQueryParameter(key, value);
    }

    /**
     * POST 请求 把k-v拼到请求体里
     *
     * @param key
     * @param value
     */
    public void addFieldParameter(String key, String value) {
//        if (formBuilder != null) {//hasBody 已经创建formBuilder，这里省略
        formBuilder.add(key, value);
//        }
    }

    public static class Builder {

        private static final String TAG = "ServiceMethod";
        private final MyRetrofit mRetrofit;
        private final Annotation[] methodAnnotations;
        private final Annotation[][] parameterAnnotations;
        private String httpMethod;
        private String relativeUrl;
        private boolean hasBody;
        private ParameterHandler[] parameterHandlers;

        //通过反射method，获取method上的注解信息
        public Builder(MyRetrofit retrofit, Method method) {
            this.mRetrofit = retrofit;
            //获取方法上的所有注解
            methodAnnotations = method.getAnnotations();
            //获取方法参数的所有注解(一个参数可以有多个注解，一个方法会有多个参数)
            parameterAnnotations = method.getParameterAnnotations();

        }

        /**
         * 1. 解析方法上的注解
         * 2. 解析方法参数上的注解
         *
         * @return
         */
        public ServiceMethod build() {

            //解析方法上的注解
            for (Annotation annotation : methodAnnotations) {
                System.out.println("  xxxxxx   "+annotation.toString());
                if (annotation instanceof GET) {
                    //记录当前请求方式
                    this.httpMethod = "GET";
                    //记录当前请求url的path
                    this.relativeUrl = ((GET) annotation).value();
                    //是否有请求体 GET没有请求体
                    this.hasBody = false;
                } else if (annotation instanceof POST) {
                    this.httpMethod = "POST";
                    this.relativeUrl = ((POST) annotation).value();
                    this.hasBody = true;
                }
            }

            /**
             * 2.解析方法参数的注解
             */
            int length = parameterAnnotations.length;
            parameterHandlers = new ParameterHandler[length];
            for (int i = 0; i < length; i++) {
                Annotation[] annotations = parameterAnnotations[i];
                for (Annotation parameter : annotations) {
                    if (parameter instanceof Field) {
                        //判断 get/post post中才会使用field字段
                        if ("POST".equals(httpMethod)) {
                            //获取参数上的注解value  请求参数的key
                            String value = ((Field) parameter).value();
                            //生成另外一个类去记录这个请求参数key //TODO 作用？
//                            new ParameterHandler.FieldParameterHandler(value);
                            parameterHandlers[i] = new ParameterHandler.FieldParameterHandler(value);
                        } else {
                            Log.i(TAG, "please use post method ,current request is get .");
                        }
                    } else if (parameter instanceof Query) {
                        if ("GET".equals(httpMethod)) {
                            String value = ((Query) parameter).value();
                            parameterHandlers[i] = new ParameterHandler.QueryParameterHandler(value);
                        } else {
                            Log.i(TAG, "please use post method ,current request is post .");
                        }
                    }
                }
            }
            return new ServiceMethod(this);
        }

    }


}
