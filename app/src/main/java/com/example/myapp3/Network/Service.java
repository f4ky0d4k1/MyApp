package com.example.myapp3.Network;

import com.example.myapp3.Network.RequestSchedule.ResponseCourses;
import com.example.myapp3.Network.RequestSchedule.ResponseDates;
import com.example.myapp3.Network.RequestSchedule.ResponseGroups;
import com.example.myapp3.Network.RequestSchedule.ResponseSubgroups;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.ResponseSemesters;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

// В POST запросах не забываем в конце /
public interface Service {

    @GET("getSchedule/getCourses")
    Single<ResponseCourses> getCourses(@Query("contract_id") Integer contract_id);

    @GET("getSchedule/getGroups")
    Single<ResponseGroups> getGroups(@Query("contract_id") Integer contract_id,
                                                @Query("course_number") Integer course_number);

    @GET("getSchedule/getSubgroups")
    Single<ResponseSubgroups> getSubgroups(@Query("contract_id") Integer contract_id,
                                                      @Query("course_number") Integer course_number,
                                                      @Query("group_number") Integer group_number);

    @GET("getSchedule/getSemesters")
    Single<ResponseSemesters> getSemesters(@Query("contract_id") Integer contract_id,
                                                      @Query("course_number") Integer course_number,
                                                      @Query("group_number") Integer group_number,
                                                      @Query("subgroup_number") Integer subgroup_number);

    @GET("getSchedule/getDates")
    Single<ResponseDates> getDates(@Query("group_id") Integer contract_id,
                                              @Query("year") Integer year,
                                              @Query("semester") Integer semester);

    @GET("getSchedule")
    Single<ResponseSchedule> getSchedule(@Query("group_id") Integer group_id,
                                                    @Query("date") String date);
}
