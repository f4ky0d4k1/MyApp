package com.example.myapp3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.ResponseSchedule.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private final ArrayList<Item> items = new ArrayList<>();
    ResponseSchedule responseSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
            responseSchedule = (ResponseSchedule) arguments.getSerializable(ResponseSchedule.class.getSimpleName());
        init();
        RecyclerView recyclerView = findViewById(R.id.list);
        ItemAdapter adapter = new ItemAdapter(this, items);
        recyclerView.setAdapter(adapter);

    }

    private void init() {
        int i = 0;
        for (List<Schedule> day : responseSchedule.getSchedule()) {
            List<InternalItem> internalItems = new ArrayList<>();
            int j = 0;
            for (Schedule lesson : day) {
                String time = Substitution.getTime(j);
                String object = lesson.getObjectName();
                String place = lesson.getPlaceName();
                if (place.equals("")) place = "Место неизвестно";
                String professor = lesson.getProfessorName();
                if (professor.equals("")) place = "Преподаватель неизвестен";
                if (!object.equals("")) {
                    InternalItem internalItem = new InternalItem(time, object, place, professor);
                    internalItems.add(internalItem);
                }
                j++;
            }
            if (internalItems.size() != 0) items.add(new Item(Substitution.getDay(i), internalItems));
            i++;
        }
    }
}
