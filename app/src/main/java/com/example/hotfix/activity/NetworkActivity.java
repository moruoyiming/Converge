package com.example.hotfix.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cocos.network.TecentNetworkApi;
import com.cocos.network.base.NetworkApi;
import com.example.hotfix.R;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        setTitle("网络请求");
        findViewById(R.id.get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TecentNetwork  applySchedulers(new BaseObserver<NewsListBean>(this, this)));
            }
        });

    }

    public void initHandler(){
        Thread th1=new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Handler handler=new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        Log.i("what","msg "+msg.what);
                    }
                };
                Message message=new Message();
                message.what=1234;
                handler.sendMessage(message);
                Looper.loop();
            }
        };
        th1.start();
    }
}
