package com.example.myapp3.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSchedule {

    @SerializedName("contractId")
    @Expose
    private Integer contractId;
    @SerializedName("courseNumber")
    @Expose
    private Integer courseNumber;
    @SerializedName("groupNumber")
    @Expose
    private Integer groupNumber;
    @SerializedName("subgroupNumber")
    @Expose
    private Integer subgroupNumber;
    @SerializedName("date")
    @Expose
    private String date;

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

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Integer getSubGroupNumber() {
        return subgroupNumber;
    }

    public void setSubGroupNumber(Integer subgroupNumber) {
        this.subgroupNumber = subgroupNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
