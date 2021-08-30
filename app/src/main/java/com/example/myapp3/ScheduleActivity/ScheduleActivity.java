package com.example.myapp3.ScheduleActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp3.Network.App;
import com.example.myapp3.Network.RequestSchedule.RequestSchedule;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.ResponseSchedule.Lesson;
import com.example.myapp3.R;
import com.example.myapp3.Util.Substitution;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ScheduleActivity extends AppCompatActivity {

    App app;
    CompositeDisposable disposable = new CompositeDisposable();
    GestureDetectorCompat lSwipeDetector;
    ResponseSchedule responseSchedule;
    RequestSchedule requestSchedule;
    Context context;

    ConstraintLayout mainLayout;
    RecyclerView recyclerView;

    Integer pos;
    List<String> dates;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            responseSchedule = (ResponseSchedule) arguments.getSerializable(ResponseSchedule.class.getSimpleName());
            requestSchedule = (RequestSchedule) arguments.getSerializable(RequestSchedule.class.getSimpleName());
            dates = (ArrayList<String>) arguments.getSerializable("dates");
            pos = (Integer) arguments.getSerializable("pos");
        }
        lSwipeDetector = new GestureDetectorCompat(this, new MyGestureListener());
        mainLayout = findViewById(R.id.schedule);
        mainLayout.setOnTouchListener((v, event) -> lSwipeDetector.onTouchEvent(event));
        recyclerView = findViewById(R.id.schedule_list);
        app = (App) getApplication();
        context = this;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() {
        ArrayList<ScheduleItem> scheduleItems = new ArrayList<>();
        int i = 0;
        LocalDate date = LocalDate.parse(requestSchedule.getDate());
        for (List<Lesson> day : responseSchedule.getSchedule()) {
            List<ScheduleInternalItem> scheduleInternalItems = new ArrayList<>();
            int j = 0;
            for (Lesson lesson : day) {
                String time = Substitution.getTime(j);
                String object = lesson.getObjectName();
                String place = lesson.getPlace();
                if (place == null || place.equals("")) place = "Место неизвестно";
                String professor = lesson.getProfessorName();
                if (professor == null || professor.equals("")) professor = "Преподаватель неизвестен";
                if (object != null && !object.equals("")) {
                    ScheduleInternalItem scheduleInternalItem = new ScheduleInternalItem(time, object, place, professor);
                    scheduleInternalItems.add(scheduleInternalItem);
                }
                j++;
            }
            if (scheduleInternalItems.size() != 0)
                scheduleItems.add(new ScheduleItem(Substitution.getDay(i) + " (" + date.plusDays(i).toString() + ")", scheduleInternalItems));
            i++;
        }
        ScheduleItemAdapter adapter = new ScheduleItemAdapter(this, scheduleItems);
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void nextSlide() {
        disposable.add(app.getNetworkService().getApi()
                .getSchedule(requestSchedule.getGroupId(), requestSchedule.getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response, throwable) -> {
                    if (throwable != null)
                        Toast.makeText(context, "Data loading error", Toast.LENGTH_SHORT).show();
                    else {
                        responseSchedule = response;
                        init();
                    }
                })
        );
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(e1.getY() - e2.getY()) > 30)
                return false;
            if (e1.getX() - e2.getX() > 100)
                if (dates.size() > pos + 1) {
                    requestSchedule.setDate(dates.get(++pos));
                    nextSlide();
                }
            if (e2.getX() - e1.getX() > 100)
                if (pos > 0) {
                    requestSchedule.setDate(dates.get(--pos));
                    nextSlide();
                }
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        super.dispatchTouchEvent(ev);
        return lSwipeDetector.onTouchEvent(ev);
    }
}