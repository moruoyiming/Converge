package com.example.converge.note.jetpack.livedata;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.converge.R;
import com.example.converge.note.jetpack.livedata.databus.LiveDataBusX;


public class TestLiveDataBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedatabus);
//        LiveDataBus.getInstance().with("data", String.class)
//                    .observe(this, new Observer<String>() {
//                        @Override
//                        public void onChanged(String s) {
//                            if(s!=null)
//                            Toast.makeText(TestLiveDataBusActivity.this, s, Toast.LENGTH_SHORT).show();
//                        }
//                    });

        LiveDataBusX.getInstance().with("data", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if(s!=null){
//                            Log.i("jett","222");
                            Toast.makeText(TestLiveDataBusActivity.this, s, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
