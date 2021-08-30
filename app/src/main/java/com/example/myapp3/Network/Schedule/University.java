package com.example.myapp3.Network.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class University implements Serializable {

    private static final long serialVersionUID = 2934010248934323745L;

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("all")
    @Expose
    private boolean all;
    @SerializedName("faculties")
    @Expose
    private List<Faculty> faculties;

    private Integer selected;

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

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Faculty getSelectedFaculty() {
        if (selected != null && selected >= 0 && selected < faculties.size())
            return faculties.get(selected);
        return null;
    }

}
