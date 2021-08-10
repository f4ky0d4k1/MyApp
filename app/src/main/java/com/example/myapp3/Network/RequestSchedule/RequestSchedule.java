package com.example.myapp3.Network.RequestSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestSchedule implements Serializable {

    private static final long serialVersionUID = 2934010248930559744L;

    @SerializedName("facultyId")
    @Expose
    private Integer facultyId;
    @SerializedName("contractId")
    @Expose
    private Integer contractId;
    @SerializedName("courseNumber")
    @Expose
    private Integer courseNumber;
    @SerializedName("groupNumber")
    @Expose
    private String groupNumber;
    @SerializedName("subgroupNumber")
    @Expose
    private Integer subgroupNumber;
    @SerializedName("groupId")
    @Expose
    private Integer groupId;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("semester")
    @Expose
    private Integer semester;
    @SerializedName("date")
    @Expose
    private String date;

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Integer getSubgroupNumber() {
        return subgroupNumber;
    }

    public void setSubgroupNumber(Integer subgroupNumber) {
        this.subgroupNumber = subgroupNumber;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void clearGarbage() {
        boolean flag = false;
        if (facultyId == null) flag = true;
        if (flag) contractId = null;
        else if(contractId == null) flag = true;
        if (flag) courseNumber = null;
        else if(courseNumber == null) flag = true;
        if (flag) groupNumber = null;
        else if(groupNumber == null) flag = true;
        if (flag) subgroupNumber = null;
        else if(subgroupNumber == null) flag = true;
        if (flag) groupId = null;
        else if(groupId == null) flag = true;
        if (flag) year = null;
        else if(year == null) flag = true;
        if (flag) semester = null;
        else if(semester == null) flag = true;
        if (flag) date = null;
    }

}

