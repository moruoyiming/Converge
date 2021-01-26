//package com.example.hotfix.note.frame.hilt;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import com.example.hotfix.MyApplication;
//import com.example.hotfix.R;
//import com.example.hotfix.note.frame.hilt.bean.ResponceData;
//import com.example.hotfix.note.frame.hilt.httpprocessor.HttpCallback;
//import com.example.hotfix.note.frame.hilt.httpprocessor.IHttpProcessor;
//
//import java.util.HashMap;
//
//import dagger.hilt.android.AndroidEntryPoint;
//
//@AndroidEntryPoint
//public class HiltDemoActivity extends AppCompatActivity {
//
////    @BindOkhttp
////    @Inject
////    IHttpProcessor iHttpProcessor;
//
////    @BindVolley
////    @Inject
////    IHttpProcessor iHttpProcessor;
//
//    //    @BindXUtils
////    @Inject
////    IHttpProcessor iHttpProcessor;
//    IHttpProcessor iHttpProcessor;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        iHttpProcessor=((MyApplication)getApplication()).getiHttpProcessor();
//    }
//
//    public void click(View view) {
//        String url = "https://v.juhe.cn/historyWeather/citys";
//        HashMap<String, Object> params = new HashMap<>();
//        //https://v.juhe.cn/historyWeather/citys?&province_id=2&key=bb52107206585ab074f5e59a8c73875b
//        params.put("province_id", "2");
//        params.put("key", "bb52107206585ab074f5e59a8c73875b");
//        iHttpProcessor.post(url, params, new HttpCallback<ResponceData>() {
//            @Override
//            public void onSuccess(ResponceData objResult) {
//                Toast.makeText(HiltDemoActivity.this, objResult.getResultcode(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}