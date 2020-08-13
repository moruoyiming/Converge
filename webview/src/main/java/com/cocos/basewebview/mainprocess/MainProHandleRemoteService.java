package com.cocos.basewebview.mainprocess;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MainProHandleRemoteService extends Service {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        int pid = android.os.Process.myPid();
        Log.d("", String.format("MainProHandleRemoteService: %s", "当前进程ID为：" + pid + "----" + "客户端与服务端连接成功，服务端返回BinderPool.BinderPoolImpl 对象"));
        return new MainProAidlInterface(this);
    }
}
