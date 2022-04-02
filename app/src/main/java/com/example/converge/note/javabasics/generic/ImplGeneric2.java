package com.example.converge.note.javabasics.generic;


/**
 * 泛型接口实现方式2
 * 直接指定类型
 */
public class ImplGeneric2<String> implements GenericInterface<String> {
    @Override
    public String next() {
        return null;
    }
}
