package com.example.basics.designpattern.my.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //静态代理
        //国内购买服务
        Purchase gnPurchase = new GnPurchase();
        //海外代理服务
        GwPurchase gwPurchase = new GwPurchase();
        //购买服务代理 1v多个
        Agent agent = new Agent();
        //代理国内购买服务
        agent.setGnPurchase(gnPurchase);
        agent.buy(100);
        //代理国外购买服务
        agent.setGnPurchase(gwPurchase);
        agent.buy(100);

        //国外代理服务 不光可以购买服务，还需要卖出服务时，需要运用动态代理
        //如果是用静态代理方式的话，需要生成一个卖出服务代理对象SaleAgent
        SaleAgent saleAgent = new SaleAgent();
        saleAgent.setPurchase(gwPurchase);
        saleAgent.sell(100);

        //动态代理  一个代理类实现全部的代理功能
        final GwPurchase gw = new GwPurchase();
        //动态代理生成的Object对象，去调用方法时，由InvocationHandle监听，当方法被调用，会传递invoke 方法中Method
        //new Class[]{Purchase.class, Sale.class} 代理多个接口
        Object proxyObject = Proxy.newProxyInstance(ProxyTest.class.getClassLoader()
                , new Class[]{Purchase.class, Sale.class}
                , new InvocationHandler() {//作用，回调作用
                    @Override//proxy 就之当前代理对象，method 为代理调用的方法 buy/sell   args 为调用方式时传入的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理对象 调用代理方法" + method + " args " + args[0]);
                        return method.invoke(gw, args);//gw 需要代理的真实对象
                    }
                });// Object o 为生成的代理对象
        // 当调用代理对象的函数时，会回调InvocationHandler内部类中的invoke函数，并最终回调代理对象所代理的真实对象函数、
        // 在invoke函数中，实现代理逻辑。
        Purchase purchase = (Purchase) proxyObject;
        purchase.buy(100);

        Sale sale = (Sale) proxyObject;
        sale.sell(100);
    }
}
