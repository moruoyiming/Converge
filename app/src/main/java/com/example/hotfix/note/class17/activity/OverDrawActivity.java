package com.example.hotfix.note.class17.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.note.class17.view.OverdrawView;


public class OverDrawActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new OverdrawView(this));


    }

}
