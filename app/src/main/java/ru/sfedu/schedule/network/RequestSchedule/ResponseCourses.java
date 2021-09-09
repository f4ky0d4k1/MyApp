package ru.sfedu.schedule.network.RequestSchedule;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCourses {

    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("courses")
    @Expose
    private List<String> courses;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}



