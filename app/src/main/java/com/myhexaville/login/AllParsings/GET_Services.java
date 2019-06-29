package com.myhexaville.login.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GET_Services {
    @SerializedName("data")
    @Expose
    private List<GET_Services_Data> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<GET_Services_Data> getData() {
        return data;
    }

    public void setData(List<GET_Services_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
