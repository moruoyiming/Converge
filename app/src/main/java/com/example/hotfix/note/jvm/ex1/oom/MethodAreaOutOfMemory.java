package com.example.hotfix.note.jvm.ex1.oom;

/**
 * cglib动态生成
 * Enhancer中 setSuperClass和setCallback, 设置好了SuperClass后, 可以使用create制作代理对象了
 * 限制方法区的大小导致的内存溢出
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * */
public class MethodAreaOutOfMemory {

    public static void main(String[] args) {
        while (true) {
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(TestObject.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
//                    return arg3.invokeSuper(arg0, arg2);
//                }
//            });
//            enhancer.create();
        }
    }

    public static class TestObject {
        private double a = 34.53;
        private Integer b = 9999999;
    }
}
