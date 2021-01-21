package com.example.hotfix.note.hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.httpprocessor_hilt.annotation.BindOkhttp;
import com.example.httpprocessor_hilt.annotation.BindVolley;
import com.example.httpprocessor_hilt.annotation.BindXUtils;
import com.example.httpprocessor_hilt.bean.ResponceData;
import com.example.httpprocessor_hilt.httpprocessor.HttpCallback;
import com.example.httpprocessor_hilt.httpprocessor.IHttpProcessor;
import com.example.httpprocessor_hilt.httpprocessor.MyApplication;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

//    @BindOkhttp
//    @Inject
//    IHttpProcessor iHttpProcessor;

//    @BindVolley
//    @Inject
//    IHttpProcessor iHttpProcessor;

    //    @BindXUtils
//    @Inject
//    IHttpProcessor iHttpProcessor;
    IHttpProcessor iHttpProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iHttpProcessor=((MyApplication)getApplication()).getiHttpProcessor();
    }

    public void click(View view) {
        String url = "https://v.juhe.cn/historyWeather/citys";
        HashMap<String, Object> params = new HashMap<>();
        //https://v.juhe.cn/historyWeather/citys?&province_id=2&key=bb52107206585ab074f5e59a8c73875b
        params.put("province_id", "2");
        params.put("key", "bb52107206585ab074f5e59a8c73875b");
        iHttpProcessor.post(url, params, new HttpCallback<ResponceData>() {
            @Override
            public void onSuccess(ResponceData objResult) {
                Toast.makeText(MainActivity.this, objResult.getResultcode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}