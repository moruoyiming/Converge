package com.example.hotfix;

import android.app.Application;

import com.cocos.basewebview.command.ToastCommand;
import com.cocos.basewebview.mainprocess.CommandsManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CommandsManager.getInstance().registerCommand(new AccountLevelCommands());
        CommandsManager.getInstance().registerCommand(new BaseLevelCommands());
//        Hotfix.fix(this,"/sdcard/patch.jar");
    }
}
