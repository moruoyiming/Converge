package com.example.hotfix.note.javabasics.inject.retrofitdemo.api;

import com.example.hotfix.note.javabasics.inject.retrofitdemo.annotation.Field;
import com.example.hotfix.note.javabasics.inject.retrofitdemo.annotation.GET;
import com.example.hotfix.note.javabasics.inject.retrofitdemo.annotation.POST;
import com.example.hotfix.note.javabasics.inject.retrofitdemo.annotation.Query;

import okhttp3.Call;


public interface MyWeatherApi {

    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);

    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);
}
