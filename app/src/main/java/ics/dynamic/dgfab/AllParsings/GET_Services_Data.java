package ics.dynamic.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GET_Services_Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;

    public GET_Services_Data(String id, String typeId, String service, String image, String status) {
        this.id = id;
        this.typeId = typeId;
        this.service = service;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
