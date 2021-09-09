package ru.sfedu.schedule.network.RequestSchedule.ResponseSemesters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSemesters {

    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("semesters")
    @Expose
    private List<Semester> semesters;

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    public List<Semester> getSemesters() { return semesters; }

    public void setSemesters(List<Semester> semesters) { this.semesters = semesters; }
}
