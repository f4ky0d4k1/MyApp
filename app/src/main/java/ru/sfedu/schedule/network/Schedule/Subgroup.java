package ru.sfedu.schedule.network.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Subgroup implements Serializable {

    private static final long serialVersionUID = 2934010248934323747L;

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("access")
    @Expose
    private boolean access;
    @SerializedName("contract_id")
    @Expose
    private Integer contractId;
    @SerializedName("course_number")
    @Expose
    private Integer courseNumber;
    @SerializedName("group_number")
    @Expose
    private String groupNumber;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("subgroups_number")
    @Expose
    private String subgroupNumber;
    @SerializedName("semesters")
    @Expose
    private List<Semester> semesters;

    private Integer selected;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSubgroupNumber() {
        return subgroupNumber;
    }

    public void setSubgroupNumber(String subgroupNumber) {
        this.subgroupNumber = subgroupNumber;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Semester getSelectedSemester() {
        if (selected != null && selected >= 0 && selected < semesters.size())
            return semesters.get(selected);
        return null;
    }
}
