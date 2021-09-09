package ru.sfedu.schedule.network.Schedule;

import android.content.Context;

import ru.sfedu.schedule.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Semester implements Comparable<Semester>, Serializable {

    private static final long serialVersionUID = 2934010248934323748L;

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("semesterNumber")
    @Expose
    private Integer semesterNumber;
    @SerializedName("weeks")
    @Expose
    private List<Week> weeks;

    private Integer selected;

    public Semester(int year, int semesterNumber) {
        this.year = year;
        this.semesterNumber = semesterNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Week getSelectedWeek() {
        if (selected != null && selected >= 0 && selected < weeks.size())
            return weeks.get(selected);
        return null;
    }

    @Override
    public int compareTo(Semester that) {
        int compareYTears = this.year.compareTo(that.year);
        if (compareYTears == 0)
            return this.semesterNumber.compareTo(that.semesterNumber);
        else return compareYTears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Semester that = (Semester) o;
        return (this.year.equals(that.year) && this.semesterNumber.equals(that.semesterNumber));
    }

    public String toString(Context context) {
        context.getApplicationContext();
        return context.getString(R.string.semester_spinner_item, semesterNumber, year);
    }

}
