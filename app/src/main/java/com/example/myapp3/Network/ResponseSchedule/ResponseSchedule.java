
package com.example.myapp3.Network.ResponseSchedule;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSchedule {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("schedule")
    @Expose
    private List<List<List<ClassNumber>>> schedule = null;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<List<List<ClassNumber>>> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<List<List<ClassNumber>>> schedule) {
        this.schedule = schedule;
    }

}