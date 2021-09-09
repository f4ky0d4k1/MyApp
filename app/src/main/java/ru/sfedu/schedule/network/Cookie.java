package ru.sfedu.schedule.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cookie implements Serializable {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("userId")
    @Expose
    private Long userId;
    @SerializedName("hash")
    @Expose
    private String hash;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
