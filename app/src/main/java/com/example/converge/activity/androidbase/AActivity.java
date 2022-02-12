package com.example.converge.activity.androidbase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
public class AActivity extends Activity {

    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aa);
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(AActivity.this,BActivity.class);
//                startActivity(intent);
                methdo();
            }
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
