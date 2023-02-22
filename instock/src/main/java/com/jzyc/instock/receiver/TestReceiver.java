package com.jzyc.instock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @Date: 2022/1/28
 * @Time: 1:56 下午
 * @Author: Jian
 */
public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("thanlon","收到自定义广播");
        String info = intent.getStringExtra("info_key");//接受广播带的参数
        Log.i("thanlon", "info");
    }
}
