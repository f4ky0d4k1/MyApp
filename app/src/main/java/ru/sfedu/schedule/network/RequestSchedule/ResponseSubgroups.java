package ru.sfedu.schedule.network.RequestSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSubgroups {

    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("subgroups")
    @Expose
    private List<String> subgroups;
    @SerializedName("groupIds")
    @Expose
    private List<Integer> groupIds;

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    public List<String> getSubgroups() { return subgroups; }

    public void setSubgroups(List<String> subgroups) { this.subgroups = subgroups; }

    public List<Integer> getGroupIds() { return groupIds; }

    public Integer getGroupId(int position) { return groupIds.get(position); }

    public void setGroupIds(List<Integer> groupIds) { this.groupIds = groupIds; }
}