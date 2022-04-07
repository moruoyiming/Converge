package com.example.compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 继承 AbstractProcessor 变成注解处理程序
 * 并且需要注册信息，在main中手动创建
 * resources/META-INF.services/javax.annotation.processing.Processor
 * 注解处理器
 */
@SupportedAnnotationTypes("com.example.converge.utils.Jian")
public class MyProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 日志打印
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "jianruilin processingEnv" );
        for (TypeElement typeElement : annotations) {
            //获取需要处理的注解对象(编译的程序中使用的注解)
            messager.printMessage(Diagnostic.Kind.NOTE, "==>" + typeElement.getSimpleName());
            //获取被注解声明的元素(类、方法、属性等)
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(typeElement);
            for (Element element : elements) {
                //element.getAnnotation() 获取对应的注解，从而获得注解的元素值
                messager.printMessage(Diagnostic.Kind.NOTE, "==>" + element.getSimpleName());
            }
        }
        return false;
    }
}