package com.example.hotfix.note.class01;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/5/26 4:02 PM
 *     Description:
 * </pre>
 */
public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        TextView tv = findViewById(R.id.title);
        String title=getIntent().getStringExtra("title");
//        tv.setText(title);
//        setTitle(title);
    }
}
