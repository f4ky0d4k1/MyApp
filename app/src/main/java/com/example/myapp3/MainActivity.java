package com.example.myapp3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp3.Network.App;
import com.example.myapp3.Network.RequestSchedule.RequestSchedule;
import com.example.myapp3.Network.RequestSchedule.ResponseCourses;
import com.example.myapp3.Network.RequestSchedule.ResponseDates;
import com.example.myapp3.Network.RequestSchedule.ResponseGroups;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.ResponseSemesters;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.Year;
import com.example.myapp3.Network.RequestSchedule.ResponseSubgroups;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.ResponseSchedule.Schedule;
import com.example.myapp3.Network.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ResponseSchedule responseSchedule;
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, RequestActivity.class);
        startActivity(intent);
    }


    private void setNewView(@LayoutRes int layoutResID) {
        setContentView(layoutResID);
    }

    private void setInitialData() {
        int i = 0;
        for (List<Schedule> day :  responseSchedule.getSchedule()) {
            List<InternalItem> internalItems = new ArrayList<>();
            int j = 0;
            for (Schedule lesson : day) {
                InternalItem internalItem = new InternalItem(Substitution.getTime(j), lesson.getObjectName(), lesson.getPlaceName(), lesson.getProfessorName());
                internalItems.add(internalItem);
                j++;
            }
            items.add(new Item(Substitution.getDay(i), internalItems));
            i++;
        }
    }

}