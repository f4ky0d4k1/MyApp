package com.example.myapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapp3.Network.RequestSchedule;
import com.example.myapp3.Network.NetworkService;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
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
        requestSchedule.setGroupNumber(6);
        requestSchedule.setSubGroupNumber(0);
        requestSchedule.setDate("2021-02-06");
        // Пример запроса, ответ в response
        ResponseSchedule response;
        CompletableFuture<ResponseSchedule> future = NetworkService.getInstance()
                .getApi()
                .getSchedule(requestSchedule);
        try {
            response = future.get();
            Log.e("Response: ", response.getError());
        } catch (ExecutionException e) {
            Log.e("ExecutionException: ", String.valueOf(e));
        } catch (InterruptedException e) {
            Log.e("InterruptedException: ", String.valueOf(e));
        }
    }

}