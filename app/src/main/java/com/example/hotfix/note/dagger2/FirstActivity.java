package com.example.hotfix.note.dagger2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.hotfix.R;
import com.example.hotfix.databinding.FirstActivityBinding;
import com.example.hotfix.note.dagger2.di.DaggerHttpComponent;
import com.example.hotfix.note.dagger2.di.DaggerPresenterComponent;
import com.example.hotfix.note.dagger2.di.DatabaseModule;
import com.example.hotfix.note.dagger2.di.DatabaseObject;
import com.example.hotfix.note.dagger2.di.HttpModule;
import com.example.hotfix.note.dagger2.di.HttpObject;
import com.example.hotfix.note.dagger2.di.Presenter;

import javax.inject.Inject;

public class FirstActivity extends Activity {

    @Inject
    HttpObject httpObject;
    @Inject
    HttpObject httpObject2;
    @Inject
    DatabaseObject databaseObject;
    @Inject
    Presenter presenter;
    @Inject
    Presenter presenter2;

    FirstActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.first_activity);
        DaggerHttpComponent
                .builder()
                .httpModule(new HttpModule())
                .databaseModule(new DatabaseModule())
                //依赖其他Component
                .presenterComponent(DaggerPresenterComponent.create())
                .build()
                .injectFirstActivity(this);
        String what = httpObject.printName();
        Log.i("Dagger2", "onCreate: "+what+"  hash code "+httpObject.hashCode() +"   "+presenter.hashCode()+"  "+presenter2.hashCode());
        binding.tvDagger.setText(what+databaseObject.getFunction());
        binding.tvDagger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        httpObject2.printName();
    }
}
