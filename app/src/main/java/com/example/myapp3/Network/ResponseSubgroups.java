package com.example.myapp3.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSubgroups {

    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("subgroups")
    @Expose
    private List<Integer> subgroups;

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    public List<Integer> getSubgroups() { return subgroups; }

    public void setSubgroups(List<Integer> subgroups) { this.subgroups = subgroups; }
}