package com.example.myapp3.Network.RequestSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFaculties {
    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("facultyNames")
    @Expose
    private List<String> facultyNames;
    @SerializedName("facultyIds")
    @Expose
    private List<Integer> facultyIds;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getFacultyNames() { return facultyNames; }

    public void setFacultyNames(List<String> facultyNames) { this.facultyNames = facultyNames; }

    public List<Integer> getFacultyIds() { return facultyIds; }

    public Integer getFacultyId(int pos) { return facultyIds.get(pos); }

    public void setFacultyIds(List<Integer> facultyIds) { this.facultyIds = facultyIds; }
}
