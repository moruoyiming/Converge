package com.example.converge.note.androidbasics.ui.fish;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.converge.R;

public class FishActivity extends AppCompatActivity {

    FishRelativeLayout imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
        imageView = findViewById(R.id.iv_fish);
//        imageView.setImageDrawable(new FishDrawable());
    }
}
