package com.example.hotfix.note.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * 2.@Module用于标注提供依赖的类
 */

@Module
public class HttpModule {
    @Provides //@Provides用于标注Module所标注的类中的方法，该方法在需要提供依赖时被调用，从而把预先提供好的对象当做依赖给标注了@Inject的变量赋值；
    public HttpObject providerHttpObject(){
        return new HttpObject("GEt");
    }
}
