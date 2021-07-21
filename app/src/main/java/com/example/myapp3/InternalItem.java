package com.example.myapp3;

public class InternalItem {

    private String time;
    private String object;
    private String place;
    private String professor;

    public InternalItem(String time, String object, String place, String professor) {
        this.time = time;
        this.object = object;
        this.place = place;
        this.professor = professor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
