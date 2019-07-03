package ics.dynamic.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Type_Sub_Users {

    @SerializedName("data")
    @Expose
    private ArrayList<Type_Sub_User_Data> data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public ArrayList<Type_Sub_User_Data> getData() {
        return data;
    }

    public void setData(ArrayList<Type_Sub_User_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
