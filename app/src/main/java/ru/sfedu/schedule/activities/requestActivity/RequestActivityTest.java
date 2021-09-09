//package com.example.myapp3.RequestActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myapp3.activities.MainActivity;
//import com.example.myapp3.Network.App;
//import com.example.myapp3.Network.Cookie;
//import com.example.myapp3.Network.RequestSchedule.RequestSchedule;
//import com.example.myapp3.Network.RequestSchedule.ResponseFaculties;
//import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.Semester;
//import com.example.myapp3.Network.RequestSchedule.ResponseSubgroups;
//import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
//import com.example.myapp3.Network.Schedule.Faculty;
//import com.example.myapp3.Network.Schedule.University;
//import com.example.myapp3.activities.ProfileActivity;
//import com.example.myapp3.R;
//import com.example.myapp3.ScheduleActivity.ScheduleActivity;
//import com.example.myapp3.Util.Substitution;
//import com.example.myapp3.Util.Util;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.schedulers.Schedulers;
//
//public class RequestActivityTest extends AppCompatActivity {
//
//    App app;
//    CompositeDisposable disposable;
//    Context thisContext;
//
//    Spinner spinnerFaculty, spinnerContract, spinnerCourse, spinnerGroup, spinnerSubgroup, spinnerSemester;
//    Button buttonGetSchedule, buttonSetProfile, buttonFillFromProfile, buttonFillFromLast;
//    RecyclerView viewDates;
//
//    //RequestSchedule requestSchedule;
//    ResponseSchedule responseSchedule;
//    ResponseFaculties responseFaculties;
//    ResponseSubgroups responseSubgroups;
//
//    University university;
//
//    University request;
//
//    ArrayList<String> dates;
//    Integer datePos;
//    boolean fill;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_request);
//        app = (App) getApplication();
//        disposable = new CompositeDisposable();
//        university = new University();
//        thisContext = this;
//
//        fill = false;
//
//        spinnerFaculty = findViewById(R.id.spinner_faculty);
//        spinnerContract = findViewById(R.id.spinner_contract);
//        spinnerCourse = findViewById(R.id.spinner_course);
//        spinnerGroup = findViewById(R.id.spinner_group);
//        spinnerSubgroup = findViewById(R.id.spinner_subgroup);
//        spinnerSemester = findViewById(R.id.spinner_semester);
//        buttonGetSchedule = findViewById(R.id.button_show_schedule);
//        buttonSetProfile = findViewById(R.id.button_set_personal_information);
//        buttonFillFromProfile = findViewById(R.id.button_fill_from_personal);
//        buttonFillFromLast = findViewById(R.id.button_fill_from_last);
//        viewDates = findViewById(R.id.request_date_list);
//        viewDates.setLayoutManager(new LinearLayoutManager(thisContext));
//
//        spinnerFaculty.setEnabled(false);
//        spinnerContract.setEnabled(false);
//        spinnerCourse.setEnabled(false);
//        spinnerGroup.setEnabled(false);
//        spinnerSubgroup.setEnabled(false);
//        spinnerSemester.setEnabled(false);
//
//        buttonGetSchedule.setEnabled(false);
//        buttonGetSchedule.setOnClickListener(view -> {
//            Util.save(getApplicationContext(), request, Util.LAST_QUERY);
//            Intent intent = new Intent(thisContext, ScheduleActivity.class);
//            startActivity(intent);
//        });
//
//        buttonSetProfile.setEnabled(true);
//        buttonSetProfile.setOnClickListener(view -> {
//            Intent intent = new Intent(thisContext, ProfileActivity.class);
//            intent.putExtra(ResponseSchedule.class.getSimpleName(), responseSchedule);
//            startActivity(intent);
//        });
//
//        buttonFillFromProfile.setEnabled(true);
//        buttonFillFromProfile.setOnClickListener(view -> {
//            University newRequest = (University) Util.open(getApplicationContext(), University.class.getSimpleName(), Util.PERSONAL_FILE);
//            if (newRequest == null) {
//                Toast.makeText(thisContext, "Личная информация не найдена", Toast.LENGTH_SHORT).show();
//            } else {
//                request= newRequest;
//                fill = true;
//                init();
//            }
//        });
//
//        buttonFillFromLast.setEnabled(true);
//        buttonFillFromLast.setOnClickListener(view -> {
//            University newRequest = (University) Util.open(getApplicationContext(), University.class.getSimpleName(), Util.LAST_QUERY);
//            if (newRequest == null) {
//                Toast.makeText(thisContext, "Последний запрос не найден", Toast.LENGTH_SHORT).show();
//            } else {
//                request = newRequest;
//                fill = true;
//                init();
//            }
//        });
//
//        init();
//    }
//
//    private void init() {
//        disposable.add(app.getNetworkService().getApi().getFacultiesTest()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((university, throwable) -> {
//                    if (throwable != null) {
//                        Toast.makeText(thisContext, "Data loading error", Toast.LENGTH_SHORT).show();
//                    } else {
//                        List<Faculty> faculties = university.getFaculties();
//                        if (faculties.size() > 0 && university.getError().equals("0")) {
//                            RequestActivityTest.this.university.setFaculties(faculties);
//                            setSpinnerFaculty(faculties);
//                            Faculty faculty = request.getSelectedFaculty();
//                            if (fill && faculty != null && faculties.contains(faculty))
//                                spinnerFaculty.setSelection(faculties.indexOf(faculty) + 1);
//                            else fill = false;
//                        } else
//                            Toast.makeText(thisContext, "Answer is empty", Toast.LENGTH_SHORT).show();
//                    }
//                })
//        );
//    }
//
//    private void setSpinnerFaculty(List<Faculty> list) {
//        List<String> names = new ArrayList<>();
//        names.add(0, "");
//        for (Faculty faculty : list) names.add(faculty.getFacultyName());
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerFaculty.setAdapter(adapter);
//        spinnerFaculty.setEnabled(true);
//        spinnerFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    request.setSelected(null);
//                    request.setFaculties(null);
//                    disable(new Spinner[]{spinnerContract, spinnerCourse, spinnerGroup, spinnerSubgroup, spinnerSemester});
//                } else {
//                    position--;
//                    request.setSelected(position);
//                    disposable.add(app.getNetworkService().getApi().getContracts(requestSchedule.getFacultyId())
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe((responseContracts, throwable) -> {
//                                if (throwable != null)
//                                    Toast.makeText(thisContext, "Data loading error", Toast.LENGTH_SHORT).show();
//                                else {
//                                    int size = responseContracts.getContractIds().size();
//                                    Log.v("getContracts", "РАЗМЕР МАССИВА РАВЕН " + size);
//                                    if (size > 0 && responseContracts.getError().equals("0")) {
//                                        List<String> contracts = new ArrayList<>();
//                                        for (int contractId : responseContracts.getContractIds())
//                                            contracts.add(Substitution.getContractName(contractId));
//                                        setSpinnerContract(contracts);
//                                        Integer contractId = requestSchedule.getContractId();
//                                        if (fill && contractId != null && responseContracts.getContractIds().contains(contractId))
//                                            spinnerContract.setSelection(responseContracts.getContractIds().indexOf(contractId) + 1);
//                                        else fill = false;
//                                    } else {
//                                        requestSchedule.setContractId(null);
//                                        disable(new Spinner[]{spinnerContract, spinnerCourse, spinnerGroup, spinnerSubgroup, spinnerSemester});
//                                    }
//                                }
//                            })
//                    );
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }
//
//    private void setSpinnerContract(List<String> list) {
//        list.add(0, "");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerContract.setAdapter(adapter);
//        spinnerContract.setEnabled(true);
//        spinnerContract.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    requestSchedule.setContractId(null);
//                    disable(new Spinner[]{spinnerCourse, spinnerGroup, spinnerSubgroup, spinnerSemester});
//                } else {
//                    requestSchedule.setContractId(Substitution.getContractId((String) parent.getSelectedItem()));
//                    disposable.add(app.getNetworkService().getApi().getCourses(requestSchedule.getFacultyId(), requestSchedule.getContractId())
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe((responseCourses, throwable) -> {
//                                if (throwable != null) {
//                                    Toast.makeText(thisContext, "Data loading error", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    int size = responseCourses.getCourses().size();
//                                    Log.v("getCourses", "РАЗМЕР МАССИВА РАВЕН " + size);
//                                    if (size > 0 && responseCourses.getError().equals("0")) {
//                                        setSpinnerCourse(responseCourses.getCourses());
//                                        Integer courseNumber = requestSchedule.getCourseNumber();
//                                        if (fill && courseNumber != null && responseCourses.getCourses().contains(courseNumber.toString()))
//                                            spinnerCourse.setSelection(responseCourses.getCourses().indexOf(courseNumber.toString()));
//                                        else fill = false;
//                                    } else {
//                                        requestSchedule.setCourseNumber(null);
//                                        disable(new Spinner[]{spinnerCourse, spinnerGroup, spinnerSubgroup, spinnerSemester});
//                                    }
//                                }
//                            })
//                    );
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }
//
//    private void setSpinnerCourse(List<String> list) {
//        list.add(0, "");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCourse.setAdapter(adapter);
//        spinnerCourse.setEnabled(true);
//        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    requestSchedule.setCourseNumber(null);
//                    disable(new Spinner[]{spinnerGroup, spinnerSubgroup, spinnerSemester});
//                } else {
//                    requestSchedule.setCourseNumber(Integer.parseInt((String) parent.getSelectedItem()));
//                    disposable.add(app.getNetworkService().getApi().getGroups(requestSchedule.getFacultyId(), requestSchedule.getContractId(), requestSchedule.getCourseNumber())
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe((responseGroups, throwable) -> {
//                                if (throwable != null) {
//                                    Toast.makeText(thisContext, "Data loading error", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    int size = responseGroups.getGroups().size();
//                                    Log.v("getGroups", "РАЗМЕР МАССИВА РАВЕН " + size);
//                                    if (size > 0 && responseGroups.getError().equals("0")) {
//                                        setSpinnerGroup(responseGroups.getGroups());
//                                        String groupNumber = requestSchedule.getGroupNumber();
//                                        if (fill && groupNumber != null && responseGroups.getGroups().contains(groupNumber))
//                                            spinnerGroup.setSelection(responseGroups.getGroups().indexOf(groupNumber));
//                                        else fill = false;
//                                    } else {
//                                        requestSchedule.setGroupNumber(null);
//                                        disable(new Spinner[]{spinnerGroup, spinnerSubgroup, spinnerSemester});
//                                    }
//                                }
//                            })
//                    );
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }
//
//    private void setSpinnerGroup(List<String> list) {
//        list.add(0, "");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerGroup.setAdapter(adapter);
//        spinnerGroup.setEnabled(true);
//        spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    requestSchedule.setGroupNumber(null);
//                    disable(new Spinner[]{spinnerSubgroup, spinnerSemester});
//                } else {
//                    requestSchedule.setGroupNumber((String) parent.getSelectedItem());
//                    disposable.add(app.getNetworkService().getApi()
//                            .getSubgroups(requestSchedule.getFacultyId(), requestSchedule.getContractId(), requestSchedule.getCourseNumber(), requestSchedule.getGroupNumber())
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe((responseSubgroups, throwable) -> {
//                                if (throwable != null) {
//                                    Toast.makeText(thisContext, "Data loading error", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    int size = responseSubgroups.getSubgroups().size();
//                                    Log.v("getSubgroups", "РАЗМЕР МАССИВА РАВЕН " + size);
//                                    if (size > 0 && responseSubgroups.getError().equals("0")) {
//                                        RequestActivity.this.responseSubgroups = responseSubgroups;
//                                        setSpinnerSubgroup(responseSubgroups.getSubgroups());
//                                        Integer subgroupNumber = requestSchedule.getSubgroupNumber();
//                                        if (fill && subgroupNumber != null && responseSubgroups.getSubgroups().contains(subgroupNumber.toString()))
//                                            spinnerSubgroup.setSelection(responseSubgroups.getSubgroups().indexOf(subgroupNumber.toString()));
//                                        else fill = false;
//                                    } else {
//                                        requestSchedule.setSubgroupNumber(null);
//                                        disable(new Spinner[]{spinnerSubgroup, spinnerSemester});
//                                    }
//                                }
//                            }));
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//    }
//
//    private void setSpinnerSubgroup(List<String> list) {
//        list.add(0, "");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSubgroup.setAdapter(adapter);
//        spinnerSubgroup.setEnabled(true);
//        spinnerSubgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    requestSchedule.setSubgroupNumber(null);
//                    disable(new Spinner[]{spinnerSemester});
//                } else {
//                    position--;
//                    requestSchedule.setSubgroupNumber(Integer.parseInt((String) parent.getSelectedItem()));
//                    requestSchedule.setGroupId(responseSubgroups.getGroupId(position));
//                    disposable.add(app.getNetworkService().getApi()
//                            .getSemesters(requestSchedule.getGroupId())
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe((responseSemesters, throwable) -> {
//                                if (throwable != null) {
//                                    Toast.makeText(thisContext, "Data loading error", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    int size = responseSemesters.getSemesters().size();
//                                    Log.v("getSubgroups", "РАЗМЕР МАССИВА РАВЕН " + size);
//                                    if (size > 0 && responseSemesters.getError().equals("0")) {
//                                        Collections.sort(responseSemesters.getSemesters());
//                                        setSpinnerSemester(responseSemesters.getSemesters());
//                                        Integer year = requestSchedule.getYear();
//                                        Integer semester = requestSchedule.getSemester();
//                                        if (fill && year != null && semester != null) {
//                                            Semester semesterObject = new Semester(year, semester);
//                                            if (responseSemesters.getSemesters().contains(semesterObject))
//                                                spinnerSemester.setSelection(responseSemesters.getSemesters().indexOf(semesterObject) + 1);
//                                        } else fill = false;
//                                    } else {
//                                        requestSchedule.setSemester(null);
//                                        disable(new Spinner[]{spinnerSemester});
//                                    }
//                                }
//                            })
//                    );
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    private void setSpinnerSemester(List<Semester> list) {
//        List<String> newList = new ArrayList<>(list.size() + 1);
//        newList.add(0, "");
//        for (Semester semester : list)
//            newList.add(semester.toString(thisContext));
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, newList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSemester.setAdapter(adapter);
//        spinnerSemester.setEnabled(true);
//        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    requestSchedule.setSemester(null);
//                    disable(new Spinner[]{});
//                } else {
//                    position--;
//                    requestSchedule.setYear(list.get(position).getYear());
//                    requestSchedule.setSemester(list.get(position).getSemesterNumber());
//                    disposable.add(app.getNetworkService().getApi()
//                                    .getDates(requestSchedule.getGroupId(), requestSchedule.getYear(), requestSchedule.getSemester())
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe((responseDates, throwable) -> {
//                                        if (throwable != null) {
//                                            Toast.makeText(thisContext, "Data loading error", Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            int size = responseDates.getDates().size();
//                                            Log.v("getSubgroups", "РАЗМЕР МАССИВА РАВЕН " + size);
//                                            if (size > 0 && responseDates.getError().equals("0")) {
//                                                setViewDates(responseDates.getDates());
////                                                String date = requestSchedule.getDate();
////                                                if (fill && date != null && dates != null && dates.contains(date))
////                                                        datesView.getAdapter().getRadioButton.get(dates.indexOf(date)).setChecked(true);
////                                                fill = false;
//                                            } else {
//                                                requestSchedule.setDate(null);
//                                                disable(new Spinner[]{});
//                                            }
//                                        }
//                                    })
//                    );
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void setViewDates(List<String> list) {
//        List<RequestDateItem> items = new ArrayList<>();
//        List<RequestDateInternalItem> internalItems = new ArrayList<>();
//        dates = new ArrayList<>();
//        LocalDate dateStart = LocalDate.parse(list.get(0));
//        LocalDate dateEnd = LocalDate.parse(list.get(1));
//        dateEnd = dateEnd.plusWeeks(1);
//        int currentMonth = dateStart.getMonthValue();
//        do {
//            String firstDay = convertDate(dateStart);
//            dates.add(dateStart.toString());
//            dateStart = dateStart.plusDays(6);
//            String secondDay = convertDate(dateStart);
//            dateStart = dateStart.plusDays(1);
//            int thisMonth = dateStart.getMonthValue();
//            internalItems.add(new RequestDateInternalItem(firstDay + " – " + secondDay));
//            if (thisMonth != currentMonth) {
//                items.add(new RequestDateItem(Substitution.getMonth(currentMonth - 1), internalItems));
//                internalItems = new ArrayList<>();
//                currentMonth = thisMonth;
//            }
//        } while (dateStart.isBefore(dateEnd));
//        if (internalItems.size() > 0) {
//            items.add(new RequestDateItem(Substitution.getMonth(currentMonth - 1), internalItems));
//        }
//        RequestDateItemAdapter adapter = new RequestDateItemAdapter(thisContext, items, new View.OnClickListener() {
//            RadioButton current = null;
//
//            @Override
//            public void onClick(View view) {
//                if (view != current) {
//                    if (current != null) current.setChecked(false);
//                    current = (RadioButton) view;
//                    datePos = (int) view.getTag();
//                    requestSchedule.setDate(dates.get(datePos));
//                    disposable.add(app.getNetworkService().getApi()
//                            .getSchedule(requestSchedule.getGroupId(), requestSchedule.getDate())
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe((response, throwable) -> {
//                                if (throwable != null)
//                                    Toast.makeText(thisContext, "Data loading error", Toast.LENGTH_SHORT).show();
//                                else {
//                                    responseSchedule = response;
//                                    buttonGetSchedule.setEnabled(true);
//                                }
//                            })
//                    );
//                }
//            }
//        });
//        viewDates.setAdapter(adapter);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private String convertDate(LocalDate date) {
//        StringBuilder newDate = new StringBuilder();
//        int i = date.getDayOfMonth();
//        if (i < 10) newDate.append("0");
//        newDate.append(i).append(".");
//        i = date.getMonthValue();
//        if (i < 10) newDate.append("0");
//        newDate.append(i);
//        return newDate.toString();
//    }
//
//    private void disable(Spinner[] spinners) {
//        buttonGetSchedule.setEnabled(false);
//        for (Spinner spinner : spinners) {
//            spinner.setEnabled(false);
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//        }
//        viewDates.setAdapter(null);
//    }
//
//    @Override
//    public void onBackPressed() {
//        openQuitDialog();
//    }
//
//    private void openQuitDialog() {
//        AlertDialog.Builder quitDialog = new AlertDialog.Builder(thisContext);
//        quitDialog.setTitle(R.string.request_on_back);
//
//        quitDialog.setPositiveButton(R.string.back_yes, (dialogInterface, i) -> {
//            Util.save(getApplicationContext(), new Cookie());
//            Intent intent = new Intent(thisContext, MainActivity.class);
//            startActivity(intent);
//            finish();
//        });
//
//        quitDialog.setNegativeButton(R.string.back_no, (dialog, which) -> {
//            // TODO Auto-generated method stub
//        });
//
//        quitDialog.show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        disposable.clear();
//    }
//}