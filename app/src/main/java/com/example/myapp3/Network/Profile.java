package com.example.myapp3.Network;

import com.example.myapp3.Network.RequestSchedule.RequestSchedule;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Profile implements Serializable {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("patronymic")
    @Expose
    private String patronymic;
    @SerializedName("requestSchedule")
    @Expose
    private RequestSchedule requestSchedule;

    public Profile() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public RequestSchedule getRequestSchedule() {
        return requestSchedule;
    }

    public void setRequestSchedule(RequestSchedule requestSchedule) {
        this.requestSchedule = requestSchedule;
    }
}
