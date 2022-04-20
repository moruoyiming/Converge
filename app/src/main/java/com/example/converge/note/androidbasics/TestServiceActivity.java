package com.example.converge.note.androidbasics;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.converge.R;

/**
 * @Date: 2022/2/10
 * @Time: 4:02 下午
 * @Author: Jian
 * 由A 启动B Activity，A 为栈内复用模式，B 为标准模式，然后再次启动A 或者
 * 杀死B，说说A，B 的生命周期变化，为什么？
 * Activity onCreate
 * Activity onStart
 * Activity onResume
 * Activity onPause
 * BActivity onCreate
 * BActivity onStart
 * BActivity onResume
 * Activity onStop
 *
 * BActivity onPause
 * Activity onReStart
 * Activity onStart
 * Activity onResume
 * BActivity onStop
 * BActivity onDestroy
 *
 */
public class TestServiceActivity extends Activity {

    Handler handler;
    ServiceConnection serviceConnection;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestServiceActivity.this, TestHandlerActivity.class);
                startActivity(intent);
//                methdo();
            }
        });
        findViewById(R.id.start).setOnClickListener(v -> {
            Intent ser= new Intent(TestServiceActivity.this,DemoService.class);
            startService(ser);
        });
        findViewById(R.id.stop).setOnClickListener(v -> {
            Intent ser= new Intent(TestServiceActivity.this,DemoService.class);
            stopService(ser);
        });
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("DemoService","ServiceConnection onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("DemoService","ServiceConnection onServiceDisconnected");
            }
        };
        findViewById(R.id.bind).setOnClickListener(v -> {
            Intent ser= new Intent(TestServiceActivity.this,DemoService.class);
            bindService(ser,serviceConnection, Context.BIND_AUTO_CREATE);
        });

        Log.i("TESTS","Activity onCreate");

    }
    public void methdo(){
        Log.i("TESTS","postDelayed start");
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("TESTS","postDelayed 5000 execute");
            }
        },5000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("TESTS","postDelayed 10000 execute");
            }
        },10000);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TESTS","Activity onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TESTS","Activity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TESTS","Activity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TESTS","Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TESTS","Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TESTS","Activity onDestroy");
    }

}