package com.example.hotfix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cocos.aop.annotation.OnClickGap;
import com.cocos.basewebview.WebviewActivity;
import com.example.hotfix.R;
import com.example.hotfix.note.class02.InjectActivity;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Utils.what();
        findViewById(R.id.openWeb1).setOnClickListener(this);
        findViewById(R.id.openWeb2).setOnClickListener(this);
        findViewById(R.id.network).setOnClickListener(this);
        findViewById(R.id.lf).setOnClickListener(this);
        findViewById(R.id.permission).setOnClickListener(this);
        findViewById(R.id.annotions).setOnClickListener(this);
    }

    public void testAnnotation() {
        Log.i("what", "test custom annotation !");
    }

    @OnClickGap
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openWeb1:
                WebviewActivity.startCommonWeb(MainActivity3.this, "腾讯网", "https://xw.qq.com/?f=qqcom");
                break;
            case R.id.openWeb2:
                WebviewActivity.startCommonWeb(MainActivity3.this, "AIDL测试", "file:///android_asset/" + "aidl.html");
                break;
            case R.id.network:
                Intent intent = new Intent(MainActivity3.this, NetworkActivity.class);
                startActivity(intent);
                break;
            case R.id.lf:
                Intent intent2 = new Intent(MainActivity3.this, AActivity.class);
                startActivity(intent2);
                break;
            case R.id.permission:
                Intent per = new Intent(MainActivity3.this, PermissionActivity.class);
                startActivity(per);
                break;
            case R.id.annotions:
                Intent intent1 = new Intent(MainActivity3.this, InjectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "jianruilin");
                bundle.putString("pwd", "123456");
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
        }
    }


}
