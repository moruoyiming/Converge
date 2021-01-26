package com.example.hotfix.note.javabasics.inject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;

import java.util.List;

public class InjectActivity extends AppCompatActivity {

    @Autowired("name")
    String name;
    @Autowired("pwd")
    String pwd;
    @Autowired("")
    List<User> user;

    @InjectView(R.id.inject_tv)
    TextView textView;
    @InjectView(R.id.inject_tv2)
    TextView textView2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
//        textView = findViewById(R.id.openWeb1);
        InjectUtils.injectView(this);
        InjectUtils.JumpActivity(this);
        textView2.setText(name + "    " + pwd);
        textView.setText(user.toString());
    }
}
