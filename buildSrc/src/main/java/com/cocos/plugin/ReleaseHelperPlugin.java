package com.cocos.plugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ReleaseHelperPlugin implements Plugin<Project> {
    public static final String sPluginExtensionName = "releaseHelper";
    public static final String ANDROID_EXTENSION_NAME = "android";

    @Override
    public void apply(Project project) {
        // 将 Extension 与 gradle 中的releaseHelper 关联
        project.getExtensions().create(sPluginExtensionName, Extension.class, project);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                //Crate pgyUpload task
                //Crate SendMsgToDingTalkTask task
                //Configure PgyUpload,SendMsgToDingTalk and assembleRelease task dependency relationship

            }
        });

    }
}
