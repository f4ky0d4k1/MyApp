package com.example.myapp3.Network.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Faculty implements Serializable {

    private static final long serialVersionUID = 2934010248934323746L;

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("all")
    @Expose
    private boolean all;
    @SerializedName("access")
    @Expose
    private boolean access;
    @SerializedName("faculty_id")
    @Expose
    private Integer facultyId;
    @SerializedName("faculty_name")
    @Expose
    private String facultyName;
    @SerializedName("subgroups")
    @Expose
    private List<Subgroup> subgroups;

    private Integer selected;

    public Faculty (Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public List<Subgroup> getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(List<Subgroup> subgroups) {
        this.subgroups = subgroups;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Subgroup getSelectedSubgroup() {
        if (selected != null && selected >= 0 && selected < subgroups.size())
            return subgroups.get(selected);
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Faculty that = (Faculty) o;
        return (this.facultyId.equals(that.facultyId));
    }
}
