package com.example.converge.note.androidbasics.inject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.converge.R;

import java.util.List;

public class InjectActivity extends AppCompatActivity {

    @Autowired("name")
    String name;
    @Autowired("pwd")
    String pwd;
    @Autowired("")
    List<User> user;
    @Autowired("users")
    User[] users;

    @InjectView(R.id.inject_tv)
    TextView textView;
    @InjectView(R.id.inject_tv2)
    TextView textView2;
    @InjectView(R.id.inject_tv3)
    TextView textView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
//        textView = findViewById(R.id.openWeb1);
        InjectUtils.injectView(this);
        InjectUtils.InjectExtras(this);

        textView.setText(name + " " + pwd);
        textView2.setText(user.toString());
        textView3.setText(users[0].toString());
    }
}
