package com.example.myapp3.Network.RequestSchedule.ResponseSemesters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Year {

    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("semesters")
    @Expose
    private Integer semesters;

    public Integer getYear() { return year; }

    public void setYear(Integer error) { this.year = error; }

    public Integer getSemesters() { return semesters; }

    public void setSemesters(Integer semesters) { this.semesters = semesters; }
}
