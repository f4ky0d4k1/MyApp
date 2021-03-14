package com.example.myapp3.Network.RequestSchedule;

/*import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCourses {

    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("courses")
    @Expose
    private List<Integer> courses;

    public String getError() { return error; }

    public List<Integer> getCourses() { return courses; }

} */

import java.util.ArrayList;
import java.util.List;
public class ResponseCourses
{
    private String error;

    private List<Integer> courses;

    public void setError(String error){
        this.error = error;
    }
    public String getError(){
        return this.error;
    }
    public void setCourses(List<Integer> courses){
        this.courses = courses;
    }
    public List<Integer> getCourses(){
        return this.courses;
    }
}

