package com.cocos.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin implements Plugin<Project> {
    public static void test() {
        System.out.print("woshi chajian");
    }

    @Override
    public void apply(Project project) {
        // Register a task
//        project.tasks.register("greeting") {
//            doLast {
//                println("Hello from plugin 'gradle.plugin.greeting'");
//            }
//        }
    }
}