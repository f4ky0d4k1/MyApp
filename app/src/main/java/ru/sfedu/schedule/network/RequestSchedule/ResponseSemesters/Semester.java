package ru.sfedu.schedule.network.RequestSchedule.ResponseSemesters;

import ru.sfedu.schedule.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import android.content.Context;

public class Semester implements Comparable<Semester>{

    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("semesterNumber")
    @Expose
    private Integer semesterNumber;

    public Semester(int year, int semesterNumber) {
        this.year = year;
        this.semesterNumber = semesterNumber;
    }

    public Integer getYear() { return year; }

    public void setYear(Integer error) { this.year = error; }

    public Integer getSemesterNumber() { return semesterNumber; }

    public void setSemesterNumber(Integer semesterNumber) { this.semesterNumber = semesterNumber; }


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
