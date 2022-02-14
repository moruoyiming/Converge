package com.example.converge.note.androidheight.frame.hilt.httpprocessor;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 回调接口的一种实现  new HttpCallback<javaBean>
 */
public abstract class HttpCallback<Result> implements ICallback{
    @Override
    public void onSuccess(String result) {
        //result就是网络访问第三方框架返回的字符串
        //1.得到调用者用什么样的javaBean接收数据
        Class<?> clz=analysisClassInfo(this);
        //2.把String result转成对应的javaBean
        Gson gson=new Gson();
        Result objResult=(Result)gson.fromJson(result,clz);
        //3.objResult交给程序员
        onSuccess(objResult);
    }
    public abstract void onSuccess(Result objResult);

    private Class<?> analysisClassInfo(Object object){
        //getGenericSuperclass();
        //可以得到包含原始类型，参数化类型，数组，类型变量，基本数据类型
        Type getType=object.getClass().getGenericSuperclass();
        Type[] params=((ParameterizedType)getType).getActualTypeArguments();
        return (Class<?>)params[0];

    }


    @Override
    public void onFailure(String e) {

    }
}
