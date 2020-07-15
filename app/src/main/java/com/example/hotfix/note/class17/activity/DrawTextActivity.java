package com.example.hotfix.note.class17.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;
import com.example.hotfix.note.class17.view.CustomTextView;

public class DrawTextActivity extends AppCompatActivity {

    private CustomTextView customTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawtext);
        customTextView =findViewById(R.id.text);
        ObjectAnimator.ofFloat(customTextView,"percent",0,1).setDuration(5000).start();
    }
}
