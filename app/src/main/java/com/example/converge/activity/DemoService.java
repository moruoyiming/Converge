package com.example.converge.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Date: 2022/2/11
 * @Time: 4:37 下午
 * @Author: Jian
 */
public class DemoService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("DemoService","DemoService onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("DemoService","DemoService onStart");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("DemoService","DemoService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("DemoService","DemoService onBind");
        return null;
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("DemoService","DemoService onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("DemoService","DemoService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DemoService","DemoService onDestroy");
    }
}
