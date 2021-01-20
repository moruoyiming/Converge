package com.example.hotfix.note.dagger2;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.hotfix.R;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        DaggerHttpComponent
                .builder()
                .httpModule(new HttpModule())
                .build()
                .injectSecondActivity(this);
    }
}
