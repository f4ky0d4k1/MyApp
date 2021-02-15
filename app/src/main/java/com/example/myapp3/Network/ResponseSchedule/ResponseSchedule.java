
package com.example.myapp3.Network.ResponseSchedule;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSchedule {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("week")
    @Expose
    private Boolean week;
    @SerializedName("schedule")
    @Expose
    private List<List<ClassСharacter>> schedule = null;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getWeek() {
        return week;
    }

    public void setWeek(Boolean week) {
        this.week = week;
    }

    public List<List<ClassСharacter>> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<List<ClassСharacter>> schedule) {
        this.schedule = schedule;
    }

}