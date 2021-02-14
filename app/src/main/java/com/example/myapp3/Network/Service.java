package com.example.myapp3.Network;

import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;

import java.util.concurrent.CompletableFuture;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

// В POST запросах не забываем в конце /
public interface Service {
    @POST("getSchedule/")
    CompletableFuture<ResponseSchedule> getSchedule(@Body RequestSchedule body);
}
