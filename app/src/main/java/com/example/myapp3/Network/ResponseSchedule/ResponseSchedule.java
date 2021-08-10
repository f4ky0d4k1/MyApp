
package com.example.myapp3.Network.ResponseSchedule;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSchedule implements Serializable {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("schedule")
    @Expose
    private List<List<Schedule>> schedule;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public List<List<Schedule>> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<List<Schedule>> schedule) {
        this.schedule = schedule;
    }

}