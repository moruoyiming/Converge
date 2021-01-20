package com.example.hotfix.note.dagger2;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    public HttpObject providerDatabaseObject(){
        return new HttpObject("PUT");
    }
}
