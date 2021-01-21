package com.example.hotfix.note.dagger2.di;

import com.example.hotfix.note.dagger2.scope.UserScope;

import dagger.Module;
import dagger.Provides;

//@UserScope
@Module
public class PresenterModule {

    @UserScope
    @Provides
    public Presenter providePresenter(){
        return new Presenter("Xcxc");
    }


}
