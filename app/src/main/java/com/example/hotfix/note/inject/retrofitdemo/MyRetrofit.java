package com.example.hotfix.note.inject.retrofitdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.annotations.Nullable;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Converter;

/**
 * 学习手写 retrofit 代码
 */
public class MyRetrofit {

    public HttpUrl baseUrl;

    public @Nullable
    okhttp3.Call.Factory callFactory;

    public final List<Converter.Factory> converterFactories = new ArrayList<>();

    //缓存解析之后的接口信息
    private final Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();

    public MyRetrofit(Call.Factory callFactory, HttpUrl baseUrl) {
        this.callFactory = callFactory;
        this.baseUrl = baseUrl;
    }

    /**
     * 动态代理生成代理对象
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(final Class<T> service) {

        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override  //args就是Api中的接口方法 形参
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //当有代理对象的方法调用时，执行invoke 方法
                ServiceMethod serviceMethod = loadServiceMethod(method);
                return serviceMethod.invoke(args);
            }
        });

    }

    /**
     * 反射 获取method中的注解信息及参数注解并缓存到map中
     *
     * @param method
     */
    private ServiceMethod loadServiceMethod(Method method) {
        //先不上锁，避免synchronized的性能损失
        ServiceMethod serviceMethod = serviceMethodCache.get(method);
        if (serviceMethod != null) return serviceMethod;
        //多线程下避免重复解析
        synchronized (serviceMethodCache) {
            serviceMethod = serviceMethodCache.get(method);
            if (serviceMethod == null) {
                serviceMethod = new ServiceMethod.Builder(MyRetrofit.this, method).build();
                serviceMethodCache.put(method, serviceMethod);
            }
        }

        return serviceMethod;
    }


    /**
     * 构建者模式，将一个复杂对象的构建和它的表示分离，可以使使用者不必知道内部组成的细节。
     */
    public static final class Builder {
        private HttpUrl baseUrl;
        //Okhttp->OkhttClient
        private okhttp3.Call.Factory callFactory;  //null


        public Builder callFactory(okhttp3.Call.Factory factory) {
            this.callFactory = factory;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = HttpUrl.get(baseUrl);
            return this;
        }

        public MyRetrofit build() {
            if (baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            okhttp3.Call.Factory callFactory = this.callFactory;
            if (callFactory == null) {
                callFactory = new OkHttpClient();
            }

            return new MyRetrofit(callFactory, baseUrl);
        }
    }
}
