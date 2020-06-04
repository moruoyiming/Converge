package com.cocos.plugin;

import org.gradle.api.Project;

//中介
public class Extension {
    //    Parameters required for FIR upload
    public String pgyApiKey;
    public String appName;
    public String changeLog;
    public String iconFilePath;
    //    Parameters required for Send Message to DingTalk
    public String apiToken;
    public String msgTitle;
    public String msgContent;
    public String singleButtonTitle;
    public String singleButtonUrl;

    public Extension() {

    }

    public Extension(Project project) {

    }

    public static Extension getConfig(Project project) {
        Extension config = project.getExtensions().findByType(Extension.class);
        if (config == null) {
            config = new Extension();
        }
        return config;
    }


}
