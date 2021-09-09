package ru.sfedu.schedule.network.RequestSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGroups {

    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("groups")
    @Expose
    private List<String> groups;

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    public List<String> getGroups() { return groups; }

    public void setGroups(List<String> groups) { this.groups = groups; }
}
