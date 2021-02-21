package com.example.myapp3.Network;

import com.example.myapp3.Network.RequestSchedule.ResponseCourses;
import com.example.myapp3.Network.RequestSchedule.ResponseDates;
import com.example.myapp3.Network.RequestSchedule.ResponseGroups;
import com.example.myapp3.Network.RequestSchedule.ResponseSubgroups;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.ResponseSemesters;

import java.util.concurrent.CompletableFuture;

import retrofit2.http.GET;
import retrofit2.http.Query;

// В POST запросах не забываем в конце /
public interface Service {

    @GET("getSchedule/getCourses")
    CompletableFuture<ResponseCourses> getCourses(@Query("contract_id") Integer contract_id);

    @GET("getSchedule/getGroups")
    CompletableFuture<ResponseGroups> getGroups(@Query("contract_id") Integer contract_id,
                                                @Query("course_number") Integer course_number);

    @GET("getSchedule/getSubgroups")
    CompletableFuture<ResponseSubgroups> getSubgroups(@Query("contract_id") Integer contract_id,
                                                      @Query("course_number") Integer course_number,
                                                      @Query("group_number") Integer group_number);

    @GET("getSchedule/getSemesters")
    CompletableFuture<ResponseSemesters> getSemesters(@Query("contract_id") Integer contract_id,
                                                      @Query("course_number") Integer course_number,
                                                      @Query("group_number") Integer group_number,
                                                      @Query("subgroup_number") Integer subgroup_number);

    @GET("getSchedule/getDates")
    CompletableFuture<ResponseDates> getDates(@Query("group_id") Integer group_id,
                                              @Query("year") Integer year,
                                              @Query("semester") Integer semester);

    @GET("getSchedule")
    CompletableFuture<ResponseSchedule> getSchedule(@Query("group_id") Integer group_id,
                                                    @Query("date") String date);
}
