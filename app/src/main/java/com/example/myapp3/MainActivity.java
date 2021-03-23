package com.example.myapp3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp3.Network.App;
import com.example.myapp3.Network.RequestSchedule.RequestSchedule;
import com.example.myapp3.Network.RequestSchedule.ResponseCourses;
import com.example.myapp3.Network.RequestSchedule.ResponseDates;
import com.example.myapp3.Network.RequestSchedule.ResponseGroups;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.ResponseSemesters;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.Year;
import com.example.myapp3.Network.RequestSchedule.ResponseSubgroups;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Service service;
    App app ;
    CompositeDisposable disposable = new CompositeDisposable();
    Spinner courseSpinner, groupSpinner, subgroupSpinner, semesterSpinner, datesSpinner;
    Button button;
    RadioButton r1, r2;
    CardView card;

    int formEdu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        app = (App) getApplication();

    }


    private void init() {
        card = findViewById(R.id.card_view);
        courseSpinner = findViewById(R.id.course_spinner);
        groupSpinner = findViewById(R.id.group_spinner);
        subgroupSpinner = findViewById(R.id.subgroup_spinner);
        semesterSpinner = findViewById(R.id.semester_spinner);
        datesSpinner = findViewById(R.id.dates_spinner);
        button = findViewById(R.id.button);

        courseSpinner.setEnabled(false);
        groupSpinner.setEnabled(false);
        subgroupSpinner.setEnabled(false);
        semesterSpinner.setEnabled(false);
        datesSpinner.setEnabled(false);


        r1 = findViewById(R.id.radio_1);
        r2 = findViewById(R.id.radio_0);

        View.OnClickListener radioButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formEdu = 0;
                RadioButton rb = (RadioButton) view;
                if (rb.getId() == R.id.radio_1) {
                    formEdu = 1;
                }
                disposable.add(app.getNetworkService().getApi().getCourses(formEdu)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseCourses, Throwable>() {
                            @Override
                            public void accept(ResponseCourses responseCourses, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.v("ddd", "РАЗМЕР МАССИВА РАВЕН " + String.valueOf(responseCourses.getCourses().size()));
                                    setCourseSpinner(responseCourses.getCourses());
                                }
                            }
                        })
                );
            }
        };

        r1.setOnClickListener(radioButtonListener);
        r2.setOnClickListener(radioButtonListener);
    }

    private void setCourseSpinner(List<Integer> list) {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(adapter);
        courseSpinner.setEnabled(true);
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        disposable.add(app.getNetworkService().getApi().getGroups(formEdu, (Integer) courseSpinner.getSelectedItem())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BiConsumer<ResponseGroups, Throwable>() {
                                    @Override
                                    public void accept(ResponseGroups responseGroups, Throwable throwable) throws Exception {
                                        if (throwable != null) {
                                            Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                                        } else {
                                            setGroupSpinner(responseGroups.getGroups());
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
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(adapter);
        groupSpinner.setEnabled(true);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    disposable.add(app.getNetworkService().getApi()
                            .getSubgroups(formEdu,(Integer) courseSpinner.getSelectedItem(),(Integer) groupSpinner.getSelectedItem())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new BiConsumer<ResponseSubgroups, Throwable>() {
                                @Override
                                public void accept(ResponseSubgroups responseSubgroups, Throwable throwable) throws Exception {
                                    if (throwable != null) {
                                        Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                                    } else {
                                        setSubgroupSpinner(responseSubgroups.getSubgroups());
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
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subgroupSpinner.setAdapter(adapter);
        subgroupSpinner.setEnabled(true);
        subgroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disposable.add(app.getNetworkService().getApi()
                        .getSemesters(formEdu,(Integer) courseSpinner.getSelectedItem(),(Integer) groupSpinner.getSelectedItem(), (Integer) subgroupSpinner.getSelectedItem())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseSemesters, Throwable>() {
                            @Override
                            public void accept(ResponseSemesters responseSemesters, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                                } else {
                                    setSemesterSpinner(responseSemesters.getYears(), responseSemesters.getGroupId());
                                }
                            }
                        }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSemesterSpinner(List<Year> list, int groupId) {
        List<String> newList = new ArrayList<String>(list.size());
        for (Year year: list) {
            newList.add(String.valueOf(year.getSemesters()) + " - " + String.valueOf(year.getYear()) + " год");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, newList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(adapter);
        semesterSpinner.setEnabled(true);
        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disposable.add(app.getNetworkService().getApi()
                        .getDates(groupId, list.get(i).getYear(), list.get(i).getSemesters())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<ResponseDates, Throwable>() {
                            @Override
                            public void accept(ResponseDates responseDates, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                                } else {
                                    setDatesSpinner(responseDates.getDates(), groupId);
                                }
                            }
                        }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setDatesSpinner(List<String> list, int groupId) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        datesSpinner.setAdapter(adapter);
        datesSpinner.setEnabled(true);
        datesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        disposable.add(app.getNetworkService().getApi()
                                .getSchedule(groupId, list.get(i))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BiConsumer<ResponseSchedule, Throwable>() {
                                    @Override
                                    public void accept(ResponseSchedule responseSchedule, Throwable throwable) throws Exception {

                                    }
                        }));
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

        @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}