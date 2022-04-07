package com.example.converge.note.javabasics.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Date: 2022/4/6
 * @Time: 11:02
 * @Author: Jian
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface LanceMethod {

    String value() default "jianruilin";//如果不以value 命名，使用时需要出入 key = value 形式

    String name();

}
