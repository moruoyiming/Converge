package com.algorithm.demo.designpattern.structure.proxy;

import com.algorithm.demo.designpattern.service.OrderService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDynic {

    OrderService orderService;

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(orderService.getClass().getClassLoader(), orderService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
    }
}
