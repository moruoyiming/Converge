package com.example.hotfix;

import androidx.multidex.MultiDexApplication;

import com.cocos.basewebview.mainprocess.CommandsManager;
import com.example.hotfix.command.AccountLevelCommands;
import com.example.hotfix.command.BaseLevelCommands;

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CommandsManager.getInstance().registerCommand(new AccountLevelCommands());
        CommandsManager.getInstance().registerCommand(new BaseLevelCommands());
//        Hotfix.fix(this,"/sdcard/patch.jar");
    }
}
