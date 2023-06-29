package com.android.app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

    @SerializedName("idAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("tenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("casiAlbum")
    @Expose
    private String casiAlbum;
    @SerializedName("hinhAlbum")
    @Expose
    private String hinhAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getCasiAlbum() {
        return casiAlbum;
    }

    public void setCasiAlbum(String casiAlbum) {
        this.casiAlbum = casiAlbum;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }

}
