package ru.sfedu.schedule.activities;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.sfedu.schedule.R;
import ru.sfedu.schedule.network.App;
import ru.sfedu.schedule.network.Cookie;
import ru.sfedu.schedule.network.Profile;
import ru.sfedu.schedule.network.RequestSchedule.RequestSchedule;
import ru.sfedu.schedule.network.RequestSchedule.ResponseFaculties;
import ru.sfedu.schedule.network.RequestSchedule.ResponseSubgroups;
import ru.sfedu.schedule.utils.Substitution;
import ru.sfedu.schedule.utils.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileActivity extends AppCompatActivity {

    App app;
    CompositeDisposable disposable;
    Spinner spinnerFaculty, spinnerContract, spinnerCourse, spinnerGroup, spinnerSubgroup;
    Button buttonSave;
    EditText editSurname, editName, editPatronymic;
    TextView textSurnameHint, textNameHint, textPatronymicHint;

    RequestSchedule requestSchedule;
    Profile profile;
    ResponseFaculties responseFaculties;
    ResponseSubgroups responseSubgroups;
    Context thisContext;
    boolean fill, editSurnameOK, editNameOK, editPatronymicOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        thisContext = this;
        app = (App) getApplication();
        disposable = new CompositeDisposable();

        init();
    }

    private void preInit() {

        if (requestSchedule.getFacultyId() == null)
            requestSchedule = profile.getRequestSchedule();
        editSurname.setVisibility(View.VISIBLE);
        editName.setVisibility(View.VISIBLE);
        editPatronymic.setVisibility(View.VISIBLE);
        textSurnameHint.setVisibility(View.INVISIBLE);
        textNameHint.setVisibility(View.INVISIBLE);
        textPatronymicHint.setVisibility(View.INVISIBLE);

        editSurnameOK = true;
        editNameOK = true;
        editPatronymicOK = true;

        editSurname.setText(profile.getSurname());
        editName.setText(profile.getName());
        editPatronymic.setText(profile.getPatronymic());

        editSurname.addTextChangedListener(new TextWatcher() {

            private final Runnable post = new Runnable() {
                @Override
                public void run() {
                    textSurnameHint.setVisibility(View.INVISIBLE);
                }
            };

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String status = Util.checkText(thisContext, editSurname.getText().toString(), R.string.pattern_name, R.string.pattern_name_symbols, 0, 40);
                textSurnameHint.removeCallbacks(post);
                textSurnameHint.setText(status);
                if (!status.equals(getString(R.string.OK))) {
                    editSurnameOK = false;
                    buttonSave.setEnabled(false);
                    textSurnameHint.setTextColor(getColor(R.color.design_default_color_error));
                    editSurname.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                } else {
                    editSurnameOK = true;
                    if (editNameOK && editPatronymicOK)
                        buttonSave.setEnabled(true);
                    textSurnameHint.setTextColor(getColor(R.color.teal_200));
                    editSurname.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                }
                textSurnameHint.setVisibility(View.VISIBLE);
                textSurnameHint.postDelayed(post, 3000);
            }
        });

        editName.addTextChangedListener(new TextWatcher() {

            private final Runnable post = new Runnable() {
                @Override
                public void run() {
                    textNameHint.setVisibility(View.INVISIBLE);
                }
            };

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String status = Util.checkText(thisContext, editName.getText().toString(), R.string.pattern_name, R.string.pattern_name_symbols, 0, 40);
                textNameHint.removeCallbacks(post);
                textNameHint.setText(status);
                if (!status.equals(getString(R.string.OK))) {
                    editNameOK = false;
                    buttonSave.setEnabled(false);
                    textNameHint.setTextColor(getColor(R.color.design_default_color_error));
                    editName.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                } else {
                    editNameOK = true;
                    if (editSurnameOK && editPatronymicOK)
                        buttonSave.setEnabled(true);
                    textNameHint.setTextColor(getColor(R.color.teal_200));
                    editName.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                }
                textNameHint.setVisibility(View.VISIBLE);
                textNameHint.postDelayed(post, 3000);
            }
        });

        editPatronymic.addTextChangedListener(new TextWatcher() {

            private final Runnable post = new Runnable() {
                @Override
                public void run() {
                    textPatronymicHint.setVisibility(View.INVISIBLE);
                }
            };

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String status = Util.checkText(thisContext, editPatronymic.getText().toString(), R.string.pattern_name, R.string.pattern_name_symbols, 0, 40);
                textPatronymicHint.removeCallbacks(post);
                textPatronymicHint.setText(status);
                if (!status.equals(getString(R.string.OK))) {
                    editPatronymicOK = false;
                    buttonSave.setEnabled(false);
                    textPatronymicHint.setTextColor(getColor(R.color.design_default_color_error));
                    editPatronymic.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                } else {
                    editPatronymicOK = true;
                    if (editSurnameOK && editNameOK)
                        buttonSave.setEnabled(true);
                    textPatronymicHint.setTextColor(getColor(R.color.teal_200));
                    editPatronymic.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                }
                textPatronymicHint.setVisibility(View.VISIBLE);
                textPatronymicHint.postDelayed(post, 3000);
            }
        });
    }

    private void init() {
        fill = true;

        requestSchedule = (RequestSchedule) Util.open(getApplicationContext(), RequestSchedule.class.getSimpleName(), Util.PERSONAL_FILE);
        if (requestSchedule == null) {
            requestSchedule = new RequestSchedule();
        }

        editSurname = findViewById(R.id.edit_first);
        editName = findViewById(R.id.edit_second);
        editPatronymic = findViewById(R.id.edit_third);
        textSurnameHint = findViewById(R.id.text_first_hint);
        textNameHint = findViewById(R.id.text_second_hint);
        textPatronymicHint = findViewById(R.id.text_third_hint);

        spinnerFaculty = findViewById(R.id.spinner_faculty);
        spinnerContract = findViewById(R.id.spinner_contract);
        spinnerCourse = findViewById(R.id.spinner_course);
        spinnerGroup = findViewById(R.id.spinner_group);
        spinnerSubgroup = findViewById(R.id.spinner_subgroup);
        buttonSave = findViewById(R.id.button_save);

        editSurname.setVisibility(View.GONE);
        editName.setVisibility(View.GONE);
        editPatronymic.setVisibility(View.GONE);
        textSurnameHint.setVisibility(View.GONE);
        textNameHint.setVisibility(View.GONE);
        textPatronymicHint.setVisibility(View.GONE);

        Cookie cookie = (Cookie) Util.open(getApplicationContext(), Cookie.class.getSimpleName());
        if (cookie != null) {
            disposable.add(app.getNetworkService().getApi()
                    .getProfile(cookie.getUserId(), cookie.getHash())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((profile, throwable) -> {
                        if (throwable != null) {
                            Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                        } else {
                            String error = profile.getError();
                            if (!error.equals("0")) {
                                Util.delete(getApplicationContext(), Cookie.class.getSimpleName());
                                if (error.equals("1"))
                                    Toast.makeText(thisContext, R.string.invalid_session, Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(thisContext, error, Toast.LENGTH_SHORT).show();
                            } else {
                                ProfileActivity.this.profile = profile;
                                preInit();
                            }
                        }
                    })
            );
        }

        spinnerFaculty.setEnabled(false);
        spinnerContract.setEnabled(false);
        spinnerCourse.setEnabled(false);
        spinnerGroup.setEnabled(false);
        spinnerSubgroup.setEnabled(false);
        buttonSave.setEnabled(true);
        buttonSave.setOnClickListener(view -> {
            requestSchedule.clearGarbage();
            Util.save(getApplicationContext(), requestSchedule, Util.PERSONAL_FILE);
            if (profile != null) {
                assert cookie != null;
                disposable.add(app.getNetworkService().getApi()
                        .savePersonal(cookie.getUserId(), cookie.getHash(), editName.getText().toString(),
                                editSurname.getText().toString(), editPatronymic.getText().toString(),
                                requestSchedule.getFacultyId(), requestSchedule.getContractId(),
                                requestSchedule.getCourseNumber(), requestSchedule.getGroupNumber(),
                                requestSchedule.getSubgroupNumber(), requestSchedule.getGroupId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((error, throwable) -> {
                            if (throwable != null) {
                                Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                            } else {
                                if (!error.equals("0")) {
                                    Util.delete(getApplicationContext(), Cookie.class.getSimpleName());
                                    if (error.equals("1"))
                                        Toast.makeText(thisContext, R.string.invalid_session, Toast.LENGTH_SHORT).show();
                                    else if (error.equals("2")) {
                                        Toast.makeText(thisContext, getString(R.string.crush, getString(R.string.internal_error)), Toast.LENGTH_SHORT).show();
                                    } else
                                        Toast.makeText(thisContext, error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                );
            }
        });

        disposable.add(app.getNetworkService().getApi().getFaculties()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((responseFaculties, throwable) -> {
                    if (throwable != null) {
                        Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                    } else {
                        int size = responseFaculties.getFacultyIds().size();
                        Log.v("getFaculties", "array size: " + size);
                        if (size > 0 && responseFaculties.getError().equals("0")) {
                            ProfileActivity.this.responseFaculties = responseFaculties;
                            List<String> facultyNames = responseFaculties.getFacultyNames();
                            setSpinnerFaculty(facultyNames);
                            Integer facultyId = requestSchedule.getFacultyId();
                            if (fill && facultyId != null && responseFaculties.getFacultyIds().contains(facultyId))
                                spinnerFaculty.setSelection(responseFaculties.getFacultyIds().indexOf(facultyId) + 1);
                            else fill = false;
                        } else
                            Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                    }
                })
        );

    }

    private void setSpinnerFaculty(List<String> list) {
        list.add(0, "");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaculty.setAdapter(adapter);
        spinnerFaculty.setEnabled(true);
        spinnerFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    requestSchedule.setFacultyId(null);
                    disable(new Spinner[]{spinnerContract, spinnerCourse, spinnerGroup, spinnerSubgroup});
                } else {
                    position--;
                    requestSchedule.setFacultyId(responseFaculties.getFacultyId(position));
                    disposable.add(app.getNetworkService().getApi().getContracts(requestSchedule.getFacultyId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((responseContracts, throwable) -> {
                                if (throwable != null)
                                    Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                                else {
                                    int size = responseContracts.getContractIds().size();
                                    Log.v("getContracts", "array size: " + size);
                                    if (size > 0 && responseContracts.getError().equals("0")) {
                                        List<String> contracts = new ArrayList<>();
                                        for (int contractId : responseContracts.getContractIds())
                                            contracts.add(Substitution.getContractName(contractId));
                                        setSpinnerContract(contracts);
                                        Integer contractId = requestSchedule.getContractId();
                                        if (fill && contractId != null && responseContracts.getContractIds().contains(contractId))
                                            spinnerContract.setSelection(responseContracts.getContractIds().indexOf(contractId) + 1);
                                        else fill = false;
                                    } else {
                                        requestSchedule.setContractId(null);
                                        disable(new Spinner[]{spinnerContract, spinnerCourse, spinnerGroup, spinnerSubgroup});
                                    }
                                }
                            })
                    );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSpinnerContract(List<String> list) {
        list.add(0, "");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContract.setAdapter(adapter);
        spinnerContract.setEnabled(true);
        spinnerContract.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    requestSchedule.setContractId(null);
                    disable(new Spinner[]{spinnerCourse, spinnerGroup, spinnerSubgroup});
                } else {
                    requestSchedule.setContractId(Substitution.getContractId((String) parent.getSelectedItem()));
                    disposable.add(app.getNetworkService().getApi().getCourses(requestSchedule.getFacultyId(), requestSchedule.getContractId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((responseCourses, throwable) -> {
                                if (throwable != null) {
                                    Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                                } else {
                                    int size = responseCourses.getCourses().size();
                                    Log.v("getCourses", "array size: " + size);
                                    if (size > 0 && responseCourses.getError().equals("0")) {
                                        setSpinnerCourse(responseCourses.getCourses());
                                        Integer courseNumber = requestSchedule.getCourseNumber();
                                        if (fill && courseNumber != null && responseCourses.getCourses().contains(courseNumber.toString()))
                                            spinnerCourse.setSelection(responseCourses.getCourses().indexOf(courseNumber.toString()));
                                        else fill = false;
                                    } else {
                                        requestSchedule.setCourseNumber(null);
                                        disable(new Spinner[]{spinnerCourse, spinnerGroup, spinnerSubgroup});
                                    }
                                }
                            })
                    );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSpinnerCourse(List<String> list) {
        list.add(0, "");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapter);
        spinnerCourse.setEnabled(true);
        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    requestSchedule.setCourseNumber(null);
                    disable(new Spinner[]{spinnerGroup, spinnerSubgroup});
                } else {
                    requestSchedule.setCourseNumber(Integer.parseInt((String) parent.getSelectedItem()));
                    disposable.add(app.getNetworkService().getApi().getGroups(requestSchedule.getFacultyId(), requestSchedule.getContractId(), requestSchedule.getCourseNumber())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((responseGroups, throwable) -> {
                                if (throwable != null) {
                                    Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                                } else {
                                    int size = responseGroups.getGroups().size();
                                    Log.v("getGroups", "array size:  " + size);
                                    if (size > 0 && responseGroups.getError().equals("0")) {
                                        setSpinnerGroup(responseGroups.getGroups());
                                        String groupNumber = requestSchedule.getGroupNumber();
                                        if (fill && groupNumber != null && responseGroups.getGroups().contains(groupNumber))
                                            spinnerGroup.setSelection(responseGroups.getGroups().indexOf(groupNumber));
                                        else fill = false;
                                    } else {
                                        requestSchedule.setGroupNumber(null);
                                        disable(new Spinner[]{spinnerGroup, spinnerSubgroup});
                                    }
                                }
                            })
                    );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSpinnerGroup(List<String> list) {
        list.add(0, "");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(adapter);
        spinnerGroup.setEnabled(true);
        spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    requestSchedule.setGroupNumber(null);
                    disable(new Spinner[]{spinnerSubgroup});
                } else {
                    requestSchedule.setGroupNumber((String) parent.getSelectedItem());
                    disposable.add(app.getNetworkService().getApi()
                            .getSubgroups(requestSchedule.getFacultyId(), requestSchedule.getContractId(), requestSchedule.getCourseNumber(), requestSchedule.getGroupNumber())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((responseSubgroups, throwable) -> {
                                if (throwable != null) {
                                    Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                                } else {
                                    int size = responseSubgroups.getSubgroups().size();
                                    Log.v("getSubgroups", "array size: " + size);
                                    if (size > 0 && responseSubgroups.getError().equals("0")) {
                                        ProfileActivity.this.responseSubgroups = responseSubgroups;
                                        setSpinnerSubgroup(responseSubgroups.getSubgroups());
                                        Integer subgroupNumber = requestSchedule.getSubgroupNumber();
                                        if (fill && subgroupNumber != null && responseSubgroups.getSubgroups().contains(subgroupNumber.toString()))
                                            spinnerSubgroup.setSelection(responseSubgroups.getSubgroups().indexOf(subgroupNumber.toString()));
                                        else fill = false;
                                    } else {
                                        requestSchedule.setSubgroupNumber(null);
                                        disable(new Spinner[]{spinnerSubgroup});
                                    }
                                }
                            }));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setSpinnerSubgroup(List<String> list) {
        list.add(0, "");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubgroup.setAdapter(adapter);
        spinnerSubgroup.setEnabled(true);
        spinnerSubgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    requestSchedule.setSubgroupNumber(null);
                    return;
                }
                position--;
                requestSchedule.setSubgroupNumber(Integer.parseInt((String) parent.getSelectedItem()));
                requestSchedule.setGroupId(responseSubgroups.getGroupId(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void disable(Spinner[] spinners) {
        for (Spinner spinner : spinners) {
            spinner.setEnabled(false);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}