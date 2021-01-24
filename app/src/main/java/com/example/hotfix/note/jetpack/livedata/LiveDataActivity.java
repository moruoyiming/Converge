package com.example.hotfix.note.jetpack.livedata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;
import com.example.hotfix.note.jetpack.livedata.databus.LiveDataBus;
import com.example.hotfix.note.jetpack.livedata.databus.LiveDataBusX;


public class LiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);
    }

    public void click(View view) {
        if (view.getId() == R.id.btn) {
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn2) {
            Intent intent = new Intent(this, TestLiveDataBusActivity.class);
            startActivity(intent);

            new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        //发送消息
                        LiveDataBusX.getInstance().with("data", String.class).postValue("jett"+i);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
}
