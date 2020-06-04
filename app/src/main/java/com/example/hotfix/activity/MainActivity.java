package com.example.hotfix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cocos.basewebview.WebviewActivity;
import com.example.hotfix.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Utils.what();
        findViewById(R.id.openWeb1).setOnClickListener(this);
        findViewById(R.id.openWeb2).setOnClickListener(this);
        findViewById(R.id.network).setOnClickListener(this);
        findViewById(R.id.lf).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openWeb1:
                WebviewActivity.startCommonWeb(MainActivity.this, "腾讯网", "https://xw.qq.com/?f=qqcom");
                break;
            case R.id.openWeb2:
                WebviewActivity.startCommonWeb(MainActivity.this, "AIDL测试", "file:///android_asset/"+ "aidl.html");
                break;
            case R.id.network:
                Intent intent = new Intent(MainActivity.this, NetworkActivity.class);
                startActivity(intent);
                break;
            case R.id.lf:
                Intent intent2 = new Intent(MainActivity.this, AActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
