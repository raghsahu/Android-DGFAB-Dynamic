package ics.dynamic.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registration_only {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("massage")
    @Expose
    private Registration_only_data massage;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public Registration_only_data getMassage() {
        return massage;
    }

    public void setMassage(Registration_only_data massage) {
        this.massage = massage;
    }


}
