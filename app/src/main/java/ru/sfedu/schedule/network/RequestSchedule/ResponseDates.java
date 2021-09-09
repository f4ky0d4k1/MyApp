package ru.sfedu.schedule.network.RequestSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDates {

    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("dates")
    @Expose
    private List<String> dates;

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    public List<String> getDates() { return dates; }

    public void setDates(List<String> dates) { this.dates = dates; }
}
