package com.example.compiler;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 继承 AbstractProcessor 变成注解处理程序
 * 并且需要注册信息，在main中手动创建
 * resources/META-INF.services/javax.annotation.processing.Processor
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.annotations.ARouter")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ARouterProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 日志打印
        Messager messager= processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE,"xxxxxxxdsa阿斯蒂芬xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return false;//false 通知处理器不操作 true 通知服务 不干活
    }
}