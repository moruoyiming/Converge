package com.example.hotfix.note.dagger3;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_dagger);
        DaggerMainComponent
                .create()
                .getTestSubComponent()
                .injectDaggerActivity(this);
//        DaggerMainComponent
//                .builder()
//                .mainModule(new MainModule())
//                .build();
        Log.i("Dagge2", "onCreate: "+objectForMainModule.hashCode());
        Log.i("Dagge2", "onCreate: "+objectForTestSubModule.hashCode());


    }
}
