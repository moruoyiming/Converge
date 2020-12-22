package com.example.patch;

import com.android.build.gradle.AppPlugin;

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

/**
 * 热修复打包 gradle插件
 * Gradle SDK 提供Plugin
 * 引入 apply plugin:com.example.patch.PatchPlugin
 */
public class PatchPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("======== Gradle Plugin Start =======");
        if (!project.getPlugins().hasPlugin(AppPlugin.class)) {
            throw new GradleException("PatchPlugin 必需结合android application插件使用热修复插件！");
        }

        PatchExt patchExt = project.getExtensions().create("patch",PatchExt.class);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                System.out.println("======== Gradle Plugin build finish =======");
            }
        });
        System.out.println("======== Gradle Plugin End =======");
    }
}
