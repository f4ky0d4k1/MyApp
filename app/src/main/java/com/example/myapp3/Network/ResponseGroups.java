package com.example.myapp3.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGroups {

    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("groups")
    @Expose
    private List<Integer> groups;

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    public List<Integer> getGroups() { return groups; }

    public void setGroups(List<Integer> groups) { this.groups = groups; }
}
