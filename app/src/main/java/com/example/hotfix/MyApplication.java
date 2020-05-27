package com.example.hotfix;

import android.app.Application;

import com.cocos.basewebview.mainprocess.CommandsManager;
import com.example.hotfix.command.AccountLevelCommands;
import com.example.hotfix.command.BaseLevelCommands;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CommandsManager.getInstance().registerCommand(new AccountLevelCommands());
        CommandsManager.getInstance().registerCommand(new BaseLevelCommands());
//        Hotfix.fix(this,"/sdcard/patch.jar");
    }
}
