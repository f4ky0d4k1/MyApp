package com.example.myapp3.Network;

import com.example.myapp3.Network.RequestSchedule.ResponseContracts;
import com.example.myapp3.Network.RequestSchedule.ResponseCourses;
import com.example.myapp3.Network.RequestSchedule.ResponseDates;
import com.example.myapp3.Network.RequestSchedule.ResponseFaculties;
import com.example.myapp3.Network.RequestSchedule.ResponseGroups;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.ResponseSemesters;
import com.example.myapp3.Network.RequestSchedule.ResponseSubgroups;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

// В POST запросах не забываем в конце '/'
public interface Service {

    @GET("getSchedule/getFaculties")
    Single<ResponseFaculties> getFaculties();

    @GET("getSchedule/getContracts")
    Single<ResponseContracts> getContracts(@Query("faculty_id") Integer facultyId);

    @GET("getSchedule/getCourses")
    Single<ResponseCourses> getCourses(@Query("faculty_id") Integer facultyId,
                                       @Query("contract_id") Integer contractId);

    @GET("getSchedule/getGroups")
    Single<ResponseGroups> getGroups(@Query("faculty_id") Integer facultyId,
                                     @Query("contract_id") Integer contractId,
                                     @Query("course_number") Integer courseNumber);

    @GET("getSchedule/getSubgroups")
    Single<ResponseSubgroups> getSubgroups(@Query("faculty_id") Integer facultyId,
                                           @Query("contract_id") Integer contractId,
                                           @Query("course_number") Integer courseNumber,
                                           @Query("group_number") String groupNumber);

    @GET("getSchedule/getSemesters")
    Single<ResponseSemesters> getSemesters(@Query("group_id") Integer groupId);

    @GET("getSchedule/getDates")
    Single<ResponseDates> getDates(@Query("group_id") Integer groupId,
                                   @Query("year") Integer year,
                                   @Query("semester") Integer semester);

    @GET("getSchedule")
    Single<ResponseSchedule> getSchedule(@Query("group_id") Integer groupId,
                                         @Query("date") String date);

    @GET("identify")
    Single<Cookie> identify(@Query("login") String login,
                            @Query("password") String password);

    @GET("identify/setMail")
    Single<String> setMail(@Query("mail") String mail);

    @GET("identify/confirmMail")
    Single<String> confirmMail(@Query("mail") String mail, @Query("code") String code);

    @GET("identify/register")
    Single<Cookie> register(@Query("mail") String mail,
                            @Query("code") String code,
                            @Query("password") String password);

    @GET("checkUser.php")
    Single<String> checkUser(@Query("id") Long id, @Query("hash") String hash);

    @GET("profile/save.php")
    Single<String> savePersonal(@Query("id") Long id,
                                @Query("hash") String hash,
                                @Query("name") String name,
                                @Query("surname") String surname,
                                @Query("patronymic") String patronymic,
                                @Query("faculty_id") Integer facultyId,
                                @Query("contract_id") Integer contractId,
                                @Query("course_number") Integer courseNumber,
                                @Query("group_number") String groupNumber,
                                @Query("subgroup_number") Integer subgroupNumber,
                                @Query("group_id") Integer groupId);

    @GET("profile")
    Single<Profile> getProfile(@Query("id") Long id,
                               @Query("hash") String hash);

}