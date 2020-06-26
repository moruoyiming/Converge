package com.example.hotfix.note.class02.retrofitdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyRetrofit {

    //缓存解析之后的接口信息
    private final Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();

    /**
     * 动态代理生成代理对象
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(final Class<T> service) {

        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //当有代理对象的方法调用时，执行invoke 方法
                loadServiceMethod(method);
                return null;
            }
        });

    }

    /**
     * 反射 获取method中的注解信息及参数注解并缓存到map中
     *
     * @param method
     */
    private ServiceMethod loadServiceMethod(Method method) {
        //
        ServiceMethod serviceMethod = serviceMethodCache.get(method);
        if (serviceMethod != null) return serviceMethod;
        synchronized (serviceMethodCache) {
            serviceMethod = serviceMethodCache.get(method);
            if (serviceMethod == null) {
                serviceMethod = new ServiceMethod.Builder(MyRetrofit.this, method).build();
                serviceMethodCache.put(method, serviceMethod);
            }
        }

        return serviceMethod;
    }


}
