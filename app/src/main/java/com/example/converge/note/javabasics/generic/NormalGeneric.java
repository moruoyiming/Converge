package com.example.converge.note.javabasics.generic;

/**
 * 泛型类
 * @param <T>
 */
public class NormalGeneric<T> {
    private T t;

    public NormalGeneric(T t) {
        this.t = t;
    }

    public NormalGeneric() {

    }

    public T getData() {
        return t;
    }

    public void setData(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        NormalGeneric<String> generic = new NormalGeneric<>();
        generic.setData("sdfas");
        System.out.println(generic.getData());
    }
}
