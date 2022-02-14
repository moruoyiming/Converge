package com.example.converge.note.androidbasics.ui.viewpager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.converge.R;

public class ViewPageActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        viewPager = findViewById(R.id.id_viewpager);
        viewPager.setOffscreenPageLimit(0);
    }
}
