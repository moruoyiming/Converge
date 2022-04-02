package com.example.converge.note.javabasics.generic;

/**
 * @Date: 2022/4/1
 * @Time: 14:40
 * @Author: Jian
 */
public class NormalGeneric2<T, K> {
    private T t;
    private K k;

    public NormalGeneric2() {
    }

    public NormalGeneric2(T t, K k) {
        this.t = t;
        this.k = k;
    }

    public NormalGeneric2(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

}
