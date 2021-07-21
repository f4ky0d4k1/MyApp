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

public class RequestActivity extends AppCompatActivity {

    App app;
    CompositeDisposable disposable = new CompositeDisposable();
    Spinner courseSpinner, groupSpinner, subgroupSpinner, semesterSpinner, datesSpinner, contractSpinner;
    Button button;
    CardView card;
    ArrayList<String> contracts;
    RequestSchedule requestSchedule;
    ResponseSchedule responseSchedule;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        context = this;
        init();
        app = (App) getApplication();
    }


    private void init() {


        card = findViewById(R.id.card_view);

        contractSpinner = findViewById(R.id.form_spinner);
        courseSpinner = findViewById(R.id.course_spinner);
        groupSpinner = findViewById(R.id.group_spinner);
        subgroupSpinner = findViewById(R.id.subgroup_spinner);
        semesterSpinner = findViewById(R.id.semester_spinner);
        datesSpinner = findViewById(R.id.dates_spinner);
        button = findViewById(R.id.button);

        contracts = new ArrayList<>();
        contracts.add("Бакалавриат");
        contracts.add("Специалитет");
        contracts.add("Магистратура");
        setContractSpinner(contracts);

        courseSpinner.setEnabled(false);
        groupSpinner.setEnabled(false);
        subgroupSpinner.setEnabled(false);
        semesterSpinner.setEnabled(false);
        datesSpinner.setEnabled(false);

        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScheduleActivity.class);
                intent.putExtra(ResponseSchedule.class.getSimpleName(), responseSchedule);
                startActivity(intent);
            }
        });

        requestSchedule = new RequestSchedule();
    }

    private void setContractSpinner(List<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contractSpinner.setAdapter(adapter);
        contractSpinner.setEnabled(true);
        contractSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                disposable.add(app.getNetworkService().getApi().getCourses(position)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseCourses, Throwable>() {
                            @Override
                            public void accept(ResponseCourses responseCourses, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    Toast.makeText(context, "Data loading error", Toast.LENGTH_SHORT).show();
                                } else {
                                    int size = responseCourses.getCourses().size();
                                    Log.v("getCourses", "РАЗМЕР МАССИВА РАВЕН " + size);
                                    if (size > 0) setCourseSpinner(responseCourses.getCourses());
                                    else {
                                        button.setEnabled(false);
                                        setCourseSpinner(new ArrayList<>());
                                        courseSpinner.setEnabled(false);
                                        setGroupSpinner(new ArrayList<>());
                                        groupSpinner.setEnabled(false);
                                        setSubgroupSpinner(new ArrayList<>());
                                        subgroupSpinner.setEnabled(false);
                                        setSemesterSpinner(new ArrayList<>());
                                        semesterSpinner.setEnabled(false);
                                        setDatesSpinner(new ArrayList<>());
                                        datesSpinner.setEnabled(false);
                                    }
                                }
                            }
                        })
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setCourseSpinner(List<Integer> list) {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(adapter);
        courseSpinner.setEnabled(true);
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                disposable.add(app.getNetworkService().getApi().getGroups(contractSpinner.getSelectedItemPosition(), (Integer) courseSpinner.getSelectedItem())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseGroups, Throwable>() {
                            @Override
                            public void accept(ResponseGroups responseGroups, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    Toast.makeText(context, "Data loading error", Toast.LENGTH_SHORT).show();
                                } else {
                                    int size = responseGroups.getGroups().size();
                                    Log.v("getGroups", "РАЗМЕР МАССИВА РАВЕН " + size);
                                    if (size > 0) setGroupSpinner(responseGroups.getGroups());
                                    else {
                                        button.setEnabled(false);
                                        setGroupSpinner(new ArrayList<>());
                                        groupSpinner.setEnabled(false);
                                        setSubgroupSpinner(new ArrayList<>());
                                        subgroupSpinner.setEnabled(false);
                                        setSemesterSpinner(new ArrayList<>());
                                        semesterSpinner.setEnabled(false);
                                        setDatesSpinner(new ArrayList<>());
                                        datesSpinner.setEnabled(false);
                                    }
                                }
                            }
                        })
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setGroupSpinner(List<Integer> list) {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(adapter);
        groupSpinner.setEnabled(true);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disposable.add(app.getNetworkService().getApi()
                        .getSubgroups(contractSpinner.getSelectedItemPosition(), (Integer) courseSpinner.getSelectedItem(), (Integer) groupSpinner.getSelectedItem())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseSubgroups, Throwable>() {
                            @Override
                            public void accept(ResponseSubgroups responseSubgroups, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    Toast.makeText(context, "Data loading error", Toast.LENGTH_SHORT).show();
                                } else {
                                    int size = responseSubgroups.getSubgroups().size();
                                    Log.v("getSubgroups", "РАЗМЕР МАССИВА РАВЕН " + size);
                                    if (size > 0)
                                        setSubgroupSpinner(responseSubgroups.getSubgroups());
                                    else {
                                        button.setEnabled(false);
                                        setSubgroupSpinner(new ArrayList<>());
                                        subgroupSpinner.setEnabled(false);
                                        setSemesterSpinner(new ArrayList<>());
                                        semesterSpinner.setEnabled(false);
                                        setDatesSpinner(new ArrayList<>());
                                        datesSpinner.setEnabled(false);
                                    }
                                }
                            }
                        }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setSubgroupSpinner(List<Integer> list) {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subgroupSpinner.setAdapter(adapter);
        subgroupSpinner.setEnabled(true);
        subgroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disposable.add(app.getNetworkService().getApi()
                        .getSemesters(contractSpinner.getSelectedItemPosition(), (Integer) courseSpinner.getSelectedItem(), (Integer) groupSpinner.getSelectedItem(), (Integer) subgroupSpinner.getSelectedItem())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseSemesters, Throwable>() {
                            @Override
                            public void accept(ResponseSemesters responseSemesters, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    Toast.makeText(context, "Data loading error", Toast.LENGTH_SHORT).show();
                                } else {
                                    int size = responseSemesters.getYears().size();
                                    Log.v("getSubgroups", "РАЗМЕР МАССИВА РАВЕН " + size);
                                    if (size > 0) {
                                        setSemesterSpinner(responseSemesters.getYears());
                                        requestSchedule.setGroupId(responseSemesters.getGroupId());
                                    } else {
                                        button.setEnabled(false);
                                        setSemesterSpinner(new ArrayList<>());
                                        semesterSpinner.setEnabled(false);
                                        setDatesSpinner(new ArrayList<>());
                                        datesSpinner.setEnabled(false);
                                    }
                                }
                            }
                        }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSemesterSpinner(List<Year> list) {
        List<String> newList = new ArrayList<String>(list.size());
        for (Year year : list) {
            newList.add(year.getSemesters() + " - " + year.getYear() + " год");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, newList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(adapter);
        semesterSpinner.setEnabled(true);
        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int groupId = requestSchedule.getGroupId();
                disposable.add(app.getNetworkService().getApi()
                        .getDates(groupId, list.get(i).getYear(), list.get(i).getSemesters())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseDates, Throwable>() {
                            @Override
                            public void accept(ResponseDates responseDates, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    Toast.makeText(context, "Data loading error", Toast.LENGTH_SHORT).show();
                                } else {
                                    int size = responseDates.getDates().size();
                                    Log.v("getSubgroups", "РАЗМЕР МАССИВА РАВЕН " + size);
                                    if (size > 0) {
                                        setDatesSpinner(responseDates.getDates());
                                        button.setEnabled(true);
                                    } else {
                                        button.setEnabled(false);
                                        setDatesSpinner(new ArrayList<>());
                                        datesSpinner.setEnabled(false);
                                    }
                                }
                            }
                        }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setDatesSpinner(List<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        datesSpinner.setAdapter(adapter);
        datesSpinner.setEnabled(true);
        datesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                disposable.add(app.getNetworkService().getApi()
                        .getSchedule(requestSchedule.getGroupId(), (String) parent.getSelectedItem())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseSchedule, Throwable>() {
                            @Override
                            public void accept(ResponseSchedule response, Throwable throwable) throws Exception {
                                if (throwable != null)
                                    Toast.makeText(context, "Data loading error", Toast.LENGTH_SHORT).show();
                                else
                                    responseSchedule = response;
                            }
                        }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
