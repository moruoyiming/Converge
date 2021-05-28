package com.example.converge;


import android.os.Looper;
import android.util.Log;
import android.util.Printer;
import android.view.Choreographer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cocos.base.BaseApplication;
import com.cocos.base.loadsir.CustomCallback;
import com.cocos.base.loadsir.EmptyCallback;
import com.cocos.base.loadsir.ErrorCallback;
import com.cocos.base.loadsir.LoadingCallback;
import com.cocos.base.loadsir.TimeoutCallback;
import com.cocos.base.preference.PreferencesUtil;
import com.cocos.base.utils.ToastUtil;
import com.cocos.network.base.NetworkApi;
import com.example.converge.note.frame.hilt.annotation.BindXUtils;
import com.example.converge.note.frame.hilt.httpprocessor.IHttpProcessor;
import com.example.converge.utils.NetworkRequestInfo;
import com.kingja.loadsir.core.LoadSir;

import javax.inject.Inject;

//import dagger.hilt.InstallIn;
//import dagger.hilt.android.HiltAndroidApp;
//import dagger.hilt.android.components.ApplicationComponent;
//@HiltAndroidApp
public class MyApplication extends BaseApplication {


    private static final String TAG = "Choreographer_test";

    @Override
    public void onCreate() {
        super.onCreate();
//        SkinManager.init(this);
//        Hotfix.installPatch(this,new File("/sdcard/patch.jar"));
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
        Looper.getMainLooper().setMessageLogging(new Printer() {
            private static final String START = ">>>>> Dispatching";
            private static final String END = "<<<<< Finished";

            @Override
            public void println(String x) {
                if (x.startsWith(START)) {
                    //从这里开启一个定时任务来打印方法的堆栈信息
                    LooperLog.getInstance().startPrintLog();
                }
                if (x.startsWith(END)) {
                    //从这里取消定时任务
                    LooperLog.getInstance().canclePrintLog();
                }
            }
        });
        final long starTime=System.nanoTime();
        Choreographer.getInstance().postFrameCallback(new FPSFrameCallback(System.nanoTime()));


    }


    @BindXUtils
    @Inject
    IHttpProcessor iHttpProcessor;


    public IHttpProcessor getiHttpProcessor(){
        return iHttpProcessor;
    }
}
