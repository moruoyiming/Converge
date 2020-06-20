package com.example.hotfix.note.class02;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;

public class InjectActivity extends AppCompatActivity {

    @Autowired("name")
    String name;
    @Autowired("pwd")
    String pwd;

    @SuppressLint("NonConstantResourceId")
    @InjectView(R.id.openWeb1)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        textView = findViewById(R.id.openWeb1);
        InjectUtils.injectView(this);
        InjectUtils.JumpActivity(this);
        textView.setText(name + "    " + pwd);
    }
}
