package ru.sfedu.schedule.network.RequestSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseContracts {
    @SerializedName("error")
    @Expose
    private String error = "0";
    @SerializedName("contractIds")
    @Expose
    private List<Integer> contractIds;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Integer> getContractIds() { return contractIds; }

    public void setContractIds(List<Integer> contractIds) { this.contractIds = contractIds; }
}
