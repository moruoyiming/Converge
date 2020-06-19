package com.example.compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 继承 AbstractProcessor 变成注解处理程序
 * 并且需要注册信息，在main中手动创建
 * resources/META-INF.services/javax.annotation.processing.Processor
 */
@SupportedAnnotationTypes("com.example.hotfix.utils.Jian")
public class MyProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 日志打印
        Messager messager= processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return false;
    }
}