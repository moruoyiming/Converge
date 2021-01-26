package com.example.converge.network;

import com.example.converge.beans.VoiceRequest;
import com.example.converge.beans.What;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface MnApiInterface {

    @POST("api/tts/v2")
    Observable<What> getNewsList(@Body() VoiceRequest voiceRequest);

}
