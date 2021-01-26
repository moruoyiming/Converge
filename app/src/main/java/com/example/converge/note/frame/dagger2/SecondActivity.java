 package com.example.converge.note.frame.dagger2;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.converge.R;
import com.example.converge.note.frame.dagger2.di.HttpObject;

import javax.inject.Inject;

public class SecondActivity extends Activity {
    @Inject
    HttpObject httpObject;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
//        DaggerHttpComponent
//                .builder()
//                .httpModule(new HttpModule())
//                .build()
//                .injectSecondActivity(this);
//        Log.i("Dagger2", "onCreate: "+"  hash code "+httpObject.hashCode() );

//       HttpComponent component= DaggerHttpComponent
//                .builder()
//                .httpModule(new HttpModule())
//                .build();
//        component.injectFirstActivity();
    }
}
