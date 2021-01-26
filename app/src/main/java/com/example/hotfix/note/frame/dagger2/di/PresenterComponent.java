package com.example.hotfix.note.frame.dagger2.di;

import com.example.hotfix.note.frame.dagger2.scope.UserScope;

import dagger.Component;

//@Singleton 编译出错，找不到符号  ，需要使用单例 写自己的scope
@UserScope
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {
    //使用依赖关系，就不能在使用这种语法
    // cannot be provided without an @Inject constructor or an @Provides-annotated method.
//    void inject(FirstActivity firstActivity);

    Presenter providerPresenter();

}
