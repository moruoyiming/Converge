package com.example.hotfix.note.jetpack.livedata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hotfix.R;


public class NameActivity extends AppCompatActivity {

    private NameViewModel model;

    private TextView nameTextView;
    private Button btn;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        nameTextView=findViewById(R.id.tvText);
        btn=findViewById(R.id.btn);



        //需要一个观察者来观察数据
        Observer observer=new Observer<String>(){
            @Override
            public void onChanged(String s) {
                nameTextView.setText(s);
            }
        };
        //获取到viewmodel
        model= ViewModelProviders.of(this).get(NameViewModel.class);
        //取出livedata完成订阅
        model.getCurrentName().observe(this,observer);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String anotherName="jett"+(i++);
                model.getCurrentName().setValue(anotherName);
            }
        });

    }
}
