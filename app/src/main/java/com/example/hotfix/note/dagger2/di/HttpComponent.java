package com.example.hotfix.note.dagger2.di;

import com.example.hotfix.note.dagger2.FirstActivity;
import com.example.hotfix.note.dagger2.SecondActivity;
import com.example.hotfix.note.dagger2.scope.AppScope;

import javax.inject.Singleton;

import dagger.Component;
//@Singleton
@AppScope
@Component(modules = {HttpModule.class, DatabaseModule.class}
        , dependencies = {PresenterComponent.class} )//组合依赖关系
public interface HttpComponent {

    void injectFirstActivity(FirstActivity firstActivity);

    void injectSecondActivity(SecondActivity secondActivity);

}
