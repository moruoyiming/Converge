package com.example.converge.note.androidadvanced.frame.dagger3;

import com.example.converge.note.androidadvanced.frame.dagger3.object.ObjectForTestSubModule;

import dagger.Module;
import dagger.Provides;

@Module
public class TestSubModule {

    @Provides
    public ObjectForTestSubModule providerObjectForTestSubModule() {
        return new ObjectForTestSubModule();
    }

}
