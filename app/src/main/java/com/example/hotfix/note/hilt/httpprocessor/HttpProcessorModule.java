package com.example.hotfix.note.hilt.httpprocessor;


import com.example.httpprocessor_hilt.annotation.BindOkhttp;
import com.example.httpprocessor_hilt.annotation.BindVolley;
import com.example.httpprocessor_hilt.annotation.BindXUtils;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class HttpProcessorModule {

    @BindOkhttp
    @Binds
    @Singleton
    abstract IHttpProcessor bindOkhttp(OkHttpProcessor okHttpProcessor);

    @BindVolley
    @Binds
    @Singleton
    abstract IHttpProcessor bindVolley(VolleyProcessor volleyProcessor);

    @BindXUtils
    @Binds
    @Singleton
    abstract IHttpProcessor bindXUtils(XUtilsProcessor xUtilsProcessor);
}










