package com.example.hotfix;


import com.alibaba.android.arouter.launcher.ARouter;
import com.cocos.base.BaseApplication;
import com.cocos.base.loadsir.CustomCallback;
import com.cocos.base.loadsir.EmptyCallback;
import com.cocos.base.loadsir.ErrorCallback;
import com.cocos.base.loadsir.LoadingCallback;
import com.cocos.base.loadsir.TimeoutCallback;
import com.cocos.base.preference.PreferencesUtil;
import com.cocos.base.utils.ToastUtil;
import com.cocos.basewebview.mainprocess.CommandsManager;
import com.cocos.network.base.NetworkApi;
import com.example.hotfix.command.AccountLevelCommands;
import com.example.hotfix.command.BaseLevelCommands;
import com.example.hotfix.utils.NetworkRequestInfo;
import com.example.skin.SkinManager;
import com.kingja.loadsir.core.LoadSir;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CommandsManager.getInstance().registerCommand(new AccountLevelCommands());
        CommandsManager.getInstance().registerCommand(new BaseLevelCommands());
//        Hotfix.fix(this,"/sdcard/patch.jar");
//        SkinManager.init(this);
        PreferencesUtil.init(this);
        NetworkApi.init(new NetworkRequestInfo(this));
        ToastUtil.init(this);
        ARouter.init(this);
        ARouter.openDebug();
        ARouter.openLog();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
