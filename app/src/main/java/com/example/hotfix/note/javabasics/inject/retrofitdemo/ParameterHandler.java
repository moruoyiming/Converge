package com.example.hotfix.note.javabasics.inject.retrofitdemo;

/**
 * 一个类记录请求参数key
 * 作用？
 * 在注解反射时，将key值保存起来，并放到一个list数组里。
 * 当invocationHandler执行时，通过args获取到请求方法中的形参
 * 调用handler.apply方法将value值传递过来，根据handler类型不同，将key-value回调给
 * serViceMethod.addQueryParameter 或者serViceMethod.addFieldParameter方法中。
 *
 *
 */
public abstract class ParameterHandler {


    // 传递 请求接口的value值 ，key值在创建时传入。
    abstract void apply(ServiceMethod serviceMethod, String value);


    static class QueryParameterHandler extends ParameterHandler {
        String key;

        public QueryParameterHandler(String key) {
            this.key = key;
        }

        //这里的serviceMethod 看作时回调
        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addQueryParameter(key,value);
        }
    }

    static class FieldParameterHandler extends ParameterHandler {
        String key;

        public FieldParameterHandler(String key) {
            this.key = key;
        }
        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addFieldParameter(key,value);
        }
    }
}
