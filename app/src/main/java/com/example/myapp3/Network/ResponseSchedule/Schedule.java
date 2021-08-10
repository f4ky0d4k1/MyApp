
package com.example.myapp3.Network.ResponseSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Schedule implements Serializable {

    @SerializedName("objectName")
    @Expose
    private String objectName;
    @SerializedName("professorName")
    @Expose
    private String professorName;
    @SerializedName("place")
    @Expose
    private String place;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}