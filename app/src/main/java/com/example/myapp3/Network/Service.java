package com.example.myapp3.Network;

import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.ResponseSemesters.ResponseSemesters;

import java.util.concurrent.CompletableFuture;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

// В POST запросах не забываем в конце /
public interface Service {

    @GET("getSchedule/getCourses")
    CompletableFuture<ResponseCourses> getCourses(@Query("contract_id") Integer contract_id);

    @GET("getSchedule/getGroups")
    CompletableFuture<ResponseGroups> getGroups(@Query("contract_id") Integer contract_id,
                                                @Query("course_number") Integer course_number);

    @GET("getSchedule/getSubGroups")
    CompletableFuture<ResponseSubgroups> getSubGroups(@Query("contract_id") Integer contract_id,
                                                      @Query("course_number") Integer course_number,
                                                      @Query("group_number") Integer group_number);

    @GET("getSchedule/getSemesters")
    CompletableFuture<ResponseSemesters> getSemesters(@Query("contract_id") Integer contract_id,
                                                      @Query("course_number") Integer course_number,
                                                      @Query("group_number") Integer group_number,
                                                      @Query("subgroup_number") Integer subgroup_number);

    @GET("getSchedule/getDates")
    CompletableFuture<ResponseDates> getDates(@Query("contract_id") Integer contract_id,
                                                  @Query("course_number") Integer course_number,
                                                  @Query("group_number") Integer group_number,
                                                  @Query("subgroup_number") Integer subgroup_number,
                                                  @Query("year") Integer year,
                                                  @Query("semester") Integer semester);

    @GET("getSchedule/getSchedule")
    CompletableFuture<ResponseSchedule> getSchedule(@Query("contract_id") Integer contract_id,
                                                    @Query("course_number") Integer course_number,
                                                    @Query("group_number") Integer group_number,
                                                    @Query("subgroup_number") Integer subgroup_number,
                                                    @Query("year") Integer year,
                                                    @Query("semester") Integer semester,
                                                    @Query("date") String date);
}
