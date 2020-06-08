package com.example.hotfix;

import android.app.Application;
import android.widget.Toast;

import com.cocos.base.utils.ToastUtil;
import com.cocos.basewebview.mainprocess.CommandsManager;
import com.example.hotfix.command.AccountLevelCommands;
import com.example.hotfix.command.BaseLevelCommands;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CommandsManager.getInstance().registerCommand(new AccountLevelCommands());
        CommandsManager.getInstance().registerCommand(new BaseLevelCommands());
//        Hotfix.fix(this,"/sdcard/patch.jar");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(MyApplication.this,"广告初始化完成，"+initializationStatus.getAdapterStatusMap(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
