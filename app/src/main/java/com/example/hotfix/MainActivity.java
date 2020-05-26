package com.example.hotfix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.cocos.basewebview.WebviewActivity;
import com.cocos.basewebview.utils.WebConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Utils.what();
        findViewById(R.id.openWeb1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebviewActivity.startCommonWeb(MainActivity.this, "腾讯网", "https://xw.qq.com/?f=qqcom");
            }
        });

        findViewById(R.id.openWeb2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for baselevel
                //RemoteCommonWebActivity.start(MainActivity.this, "AIDL测试", DWebView.CONTENT_SCHEME + "aidl.html");

                // for account level
                WebviewActivity.startCommonWeb(MainActivity.this, "AIDL测试", "file:///android_asset/"+ "aidl.html");
            }
        });
    }
}
