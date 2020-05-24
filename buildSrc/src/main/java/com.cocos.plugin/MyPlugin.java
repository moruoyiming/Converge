package com.cocos.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin implements Plugin<Project> {
    public static void test() {
        System.out.print("woshi chajian");
    }

    @Override
    public void apply(Project project) {
//        def extension  = project.extensions.create('greeting',GreetingPluginExtension)
//
//        project.task('hello'){
//            doLast {
//                println extension.message
//            }
//        }
    }
}