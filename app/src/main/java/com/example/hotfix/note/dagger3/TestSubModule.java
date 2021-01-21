package com.example.hotfix.note.dagger3;

import com.example.hotfix.note.dagger3.object.ObjectForTestSubModule;

import dagger.Module;
import dagger.Provides;

@Module
public class TestSubModule {

    @Provides
    public ObjectForTestSubModule providerObjectForTestSubModule() {
        return new ObjectForTestSubModule();
    }

}
