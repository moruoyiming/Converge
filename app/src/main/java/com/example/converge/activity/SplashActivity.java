package com.example.converge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 利用给SplashActivity设置带有背景的SplashTheme来避免启动白屏的问题
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getData()!=null&&!TextUtils.isEmpty(getIntent().getData().getScheme())){
            return ;
        }
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(0,0);
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(2);
//        list.add(3);
//        list.add(4);
//        List<Integer> list2 =list.subList(0, 3);
//        Log.i("Sdfas",list2.size()+" ");
    }

    @Override
    protected void onStop() {//  1. A ，onpause  2.B  oncreate 3. B onresume  4. A onStop
        super.onStop();
        finish();

    }
}
