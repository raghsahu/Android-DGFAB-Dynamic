package com.myhexaville.login.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type_Sub_User_Data {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type_id")
    @Expose
    private int typeId;
    @SerializedName("subtypename")
    @Expose
    private String subtypename;

    public Type_Sub_User_Data(int id, int typeId, String subtypename) {
        this.id = id;
        this.typeId = typeId;
        this.subtypename = subtypename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getSubtypename() {
        return subtypename;
    }

    public void setSubtypename(String subtypename) {
        this.subtypename = subtypename;
    }
}
