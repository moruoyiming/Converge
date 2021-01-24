package com.example.hotfix.note.jetpack.databinding;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.hotfix.R;
import com.example.hotfix.databinding.ActivityDatabindingBinding;

public class DataBindingActivity extends Activity {
    ActivityDatabindingBinding binding;
    //从model层来的数据
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_databinding);



        user=new User("jett","123");
        binding.setUser(user);
//        binding.setVariable(BR.name,"21341234123");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        Thread.sleep(1000);
//                        user.setName(user.getName()+"1");// view.setText(text);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

    }
}
