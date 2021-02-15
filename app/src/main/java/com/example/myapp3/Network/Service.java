package com.example.myapp3.Network;

import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;

import java.util.concurrent.CompletableFuture;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

// В POST запросах не забываем в конце /
public interface Service {
    @POST("getSchedule")
    CompletableFuture<ResponseSchedule> getSchedule(@Body RequestSchedule body);
    @GET("getSchedule/1")
    CompletableFuture<Integer> getCourseNumber(@Query("contract_id") Integer contract_id);
}
