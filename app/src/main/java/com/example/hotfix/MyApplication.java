package com.example.hotfix;


import android.content.res.Resources;

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
import com.cocos.basewebview.command.AccountLevelCommand;
import com.cocos.basewebview.command.OpenActivityCommand;
import com.example.hotfix.utils.LoadUtils;
import com.example.hotfix.utils.NetworkRequestInfo;
import com.kingja.loadsir.core.LoadSir;

public class MyApplication extends BaseApplication {

//    插件化代码
//    private Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();
//        CommandsManager.getInstance().registerCommand(new AccountLevelCommand());
//        CommandsManager.getInstance().registerCommand(new OpenActivityCommand());
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

        //获取新建 的resource资源
//        resources = LoadUtils.loadResource(this);
    }


//    //重写该方法，当resources为空时，相当于没有重写，不为空时，返回新建的resources对象
//    @Override
//    public Resources getResources() {
//        return resources == null ? super.getResources() : resources;
//    }
}
