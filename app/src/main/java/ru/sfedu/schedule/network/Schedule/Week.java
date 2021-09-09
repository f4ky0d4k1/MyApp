package ru.sfedu.schedule.network.Schedule;

import ru.sfedu.schedule.network.ResponseSchedule.Lesson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Week implements Serializable {

    private static final long serialVersionUID = 2934010248934323749L;

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("schedule")
    @Expose
    private List<List<Lesson>> schedule;

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

    public List<List<Lesson>> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<List<Lesson>> schedule) {
        this.schedule = schedule;
    }

}
