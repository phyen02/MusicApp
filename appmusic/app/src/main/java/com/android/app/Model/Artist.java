package com.android.app.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Artist implements Serializable {

    @SerializedName("idCaSi")
    @Expose
    private String idCaSi;
    @SerializedName("tenCaSi")
    @Expose
    private String tenCaSi;
    @SerializedName("hinhCaSi")
    @Expose
    private String hinhCaSi;

    public String getIdCaSi() {
        return idCaSi;
    }

    public void setIdCaSi(String idCaSi) {
        this.idCaSi = idCaSi;
    }

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    public String getHinhCaSi() {
        return hinhCaSi;
    }

    public void setHinhCaSi(String hinhCaSi) {
        this.hinhCaSi = hinhCaSi;
    }

}