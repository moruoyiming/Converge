package com.example.hotfix.note.dagger2.di;

import com.example.hotfix.note.dagger2.scope.AppScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 2.@Module用于标注提供依赖的类
 */
//@AppScope
@Module
public class HttpModule {

//    @Singleton
    @AppScope
    @Provides //@Provides用于标注Module所标注的类中的方法，该方法在需要提供依赖时被调用，从而把预先提供好的对象当做依赖给标注了@Inject的变量赋值；
    public HttpObject providerHttpObject(){
        return new HttpObject("Dagger注入");
    }
}
