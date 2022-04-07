package com.example.converge.note.javabasics.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Date: 2022/4/6
 * @Time: 10:59
 * @Author: Jian
 * <p>
 * 注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Lance {

    String value();

}
