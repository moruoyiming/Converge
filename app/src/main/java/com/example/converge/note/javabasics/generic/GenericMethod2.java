package com.example.converge.note.javabasics.generic;

/**
 * @Date: 2022/4/1
 * @Time: 14:42
 * @Author: Jian
 */
public class GenericMethod2 {

    public class Generic<T> {
        private T key;

        public Generic(T key) {
            this.key = key;
        }

        //虽然在方法中使用了泛型，但是这并不是一个泛型方法。返回值是在声明泛型类中声明过的泛型
        //所以在这个方法中可以继续使用 T 这个泛型
        public T getKey() {
            return key;
        }

        //这个方法显然时有问题的，在编译器会给我们提示的错误信息"cannot reslove symbol E"
        //因为在类的生命中并未声明泛型E，所以在使用E做形参和返回值类型时，编译器会无法识别。
//        public E setKey(E key){
//            this.key = key;
//        }
    }

    //非泛型方法
//    public void show(Generic<Number> obj) {
//
//    }
    //编译出错
//    public <T> T what(Generic<E> obj){
//
//    }



}
