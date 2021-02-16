package com.example.myapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapp3.Network.RequestSchedule;
import com.example.myapp3.Network.NetworkService;
import com.example.myapp3.Network.ResponseCourses;
import com.example.myapp3.Network.ResponseDates;
import com.example.myapp3.Network.ResponseGroups;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.ResponseSemesters.ResponseSemesters;
import com.example.myapp3.Network.ResponseSubgroups;
import com.example.myapp3.Network.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private Service service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Пример запролнения входных данных
        RequestSchedule requestSchedule = new RequestSchedule();
        requestSchedule.setContractId(1);
        requestSchedule.setCourseNumber(1);
        requestSchedule.setGroupNumber(1);
        requestSchedule.setSubgroupNumber(0);
        requestSchedule.setYear(2020);
        requestSchedule.setSubgroupNumber(1);
        requestSchedule.setDate("2021-02-08");

        // Запрос 1: GetCourses
        // Результат requestSchedule.сourseNumber = 2; в response.courses содержатся все доступные курсы для выбранной формы обучения
        {
            ResponseCourses response;
            CompletableFuture<ResponseCourses> future = NetworkService.getInstance()
                    .getApi()
                    .getCourses(requestSchedule.getContractId());
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
                Integer course = response.getCourses().get(0);
                requestSchedule.setCourseNumber(course);
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
        }

        // Запрос 2: GetGroups
        // Результат requestSchedule.groupNumber = 6; в response.groups содержатся все доступные группы для выбранного курса
        {
            ResponseGroups response;
            CompletableFuture<ResponseGroups> future = NetworkService.getInstance()
                    .getApi()
                    .getGroups(requestSchedule.getContractId(),
                            requestSchedule.getCourseNumber());
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
                Integer group = response.getGroups().get(0);
                requestSchedule.setGroupNumber(group);
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
        }

        // Запрос 3: GetSubGroups
        // Результат requestSchedule.subgroupNumber = 1; в response.subgroups содержатся все доступные подгруппы для выбранной группы
        {
            ResponseSubgroups response;
            CompletableFuture<ResponseSubgroups> future = NetworkService.getInstance()
                    .getApi()
                    .getSubGroups(requestSchedule.getContractId(),
                            requestSchedule.getCourseNumber(),
                            requestSchedule.getGroupNumber());
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
                Integer subgroup = response.getSubgroups().get(0);
                requestSchedule.setSubgroupNumber(subgroup);
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
        }

        // Запрос 4: GetSemesters
        // В ResponseSemesters.Year.Semesters значения такие: 0 - доступных семемтров нет, что является ошибкой в БД (мои кривые ркуи)
        //                                                    1 - доступен первый семестр
        //                                                    2 - доступен второй семестр
        //                                                    !!!3 - доступно оба семестра!!!
        // Результат requestSchedule.year = 2021, requestSchedule.semester = 2,; в response.groups содержатся все доступные группы для выбранного курса
        {
            ResponseSemesters response;
            CompletableFuture<ResponseSemesters> future = NetworkService.getInstance()
                    .getApi()
                    .getSemesters(requestSchedule.getContractId(),
                            requestSchedule.getCourseNumber(),
                            requestSchedule.getGroupNumber(),
                            requestSchedule.getSubgroupNumber());
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
                Integer year = response.getYears().get(0).getYear();
                Integer semester = response.getYears().get(0).getSemesters();
                requestSchedule.setSemester(semester);
                requestSchedule.setYear(year);
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
        }

        // Запрос 5: GetDates
        // В ResponseDates.Dates[0] = первый доступный понедельник
        // В ResponseDates.Dates[1] = последний доступный понедельник
        {
            ResponseDates response;
            CompletableFuture<ResponseDates> future = NetworkService.getInstance()
                    .getApi()
                    .getDates(requestSchedule.getContractId(),
                            requestSchedule.getCourseNumber(),
                            requestSchedule.getGroupNumber(),
                            requestSchedule.getSubgroupNumber(),
                            requestSchedule.getYear(),
                            requestSchedule.getSemester());
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
                String date_begin = response.getDates().get(0);
                String date_end = response.getDates().get(0);
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
        }

        // Общий запрос
        ResponseSchedule response;
        CompletableFuture<ResponseSchedule> future = NetworkService.getInstance()
                .getApi()
                .getSchedule(requestSchedule.getContractId(),
                        requestSchedule.getCourseNumber(),
                        requestSchedule.getGroupNumber(),
                        requestSchedule.getSubgroupNumber(),
                        requestSchedule.getYear(),
                        requestSchedule.getSemester(),
                        requestSchedule.getDate());
        try {
            response = future.get();
            Log.e("Response: ", response.getError());
        } catch (ExecutionException e) {
            Log.e("ExecutionException: ", String.valueOf(e));
        } catch (InterruptedException e) {
            Log.e("InterruptedException: ", String.valueOf(e));
        }


        //response[2][2]getClassСharacter();

    }
}