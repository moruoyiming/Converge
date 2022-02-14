package com.example.converge.note.androidadvanced.frame.dagger3;

import com.example.converge.note.androidadvanced.frame.dagger3.object.ObjectForMainModule;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public ObjectForMainModule providerObjectMainModule() {
        return new ObjectForMainModule();
    }

}
