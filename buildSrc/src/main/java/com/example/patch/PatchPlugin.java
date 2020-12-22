package com.example.patch;

import com.android.build.gradle.AppExtension;
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
        project.getExtensions().create("patch", PatchExt.class);
        //配置阶段，解析玩build.gradle回调此函数
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                System.out.println("======== Gradle Plugin build finish 插件=======");
                PatchExt patchExt = project.getExtensions().findByType(PatchExt.class);
                assert patchExt != null;
                System.out.println("patchExt debugOn=" + patchExt.isDebugOn());
                System.out.println("patchExt appName=" + patchExt.getApplicationName());
                //获取Android扩展
                AppExtension android = project.getExtensions().getByType(AppExtension.class);
                System.out.println(android.getApplicationVariants());
                System.out.println("======== Gradle Plugin End =======");
            }
        });

    }
}
