package com.algorithm.demo.designpattern.my.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyClass {
    public static void main(String[] args) {
        //静态代理
        Agent agent = new Agent();
        //国内代理只能买操作
        Purchase gnPurchase = new GnPurchase();
        agent.setGnPurchase(gnPurchase);
        agent.buy(100);

        //动态代理  一个代理类实现全部的代理功能
        GnPurchase gn = new GnPurchase();
        final GwPurchase gw = new GwPurchase();
        //动态代理生成的Object对象，去调用方法时，由InvocationHandle监听，当方法被调用，会传递invoke 方法中Method
        Object o = Proxy.newProxyInstance(MyClass.class.getClassLoader(), new Class[]{Purchase.class, Purchase2.class}, new InvocationHandler() {//作用，回调作用
            @Override//proxy 就之当前代理对象，method 为代理调用的方法 buy/sell   args 为调用方式时传入的参数
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理对象 调用代理方法" + method + " args " + args[0]);
                return method.invoke(gw, args);
            }
        });
        Purchase purchase = (Purchase) o;
        purchase.buy(100);

        Purchase2 purchase2 = (Purchase2) o;
        purchase2.sell(100);
    }
}
