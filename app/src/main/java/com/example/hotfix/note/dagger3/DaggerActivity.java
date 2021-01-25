package com.example.hotfix.note.dagger3;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hotfix.R;
import com.example.hotfix.note.dagger3.object.ObjectForMainModule;
import com.example.hotfix.note.dagger3.object.ObjectForTestSubModule;

import javax.inject.Inject;

public class DaggerActivity extends Activity {
    @Inject
    ObjectForMainModule objectForMainModule;
    @Inject
    ObjectForTestSubModule objectForTestSubModule;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        textView = findViewById(R.id.tv_dagger3);
        DaggerMainComponent
                .create()
                .getTestSubComponent()
                .injectDaggerActivity(this);
//        DaggerMainComponent
//                .builder()
//                .mainModule(new MainModule())
//                .build();
        textView.setText("hashCode()"+objectForMainModule.hashCode()+"  "+objectForTestSubModule.hashCode());
        Log.i("Dagge2", "onCreate: "+objectForMainModule.hashCode());
        Log.i("Dagge2", "onCreate: "+objectForTestSubModule.hashCode());
    }

}
