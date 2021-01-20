package com.example.hotfix.note.dagger2;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.hotfix.R;

import javax.inject.Inject;

public class FirstActivity extends Activity {

    @Inject
    HttpObject httpObject;
    @Inject
    HttpObject httpObject2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        DaggerHttpComponent
                .builder()
                .httpModule(new HttpModule())
                .build()
                .injectFirstActivity(this);
        httpObject.printName();

        httpObject2.printName();
    }
}
