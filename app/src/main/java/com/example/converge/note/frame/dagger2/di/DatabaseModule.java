package com.example.converge.note.frame.dagger2.di;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    public DatabaseObject providerDatabaseObject(){
        return new DatabaseObject("PUT");
    }
}
