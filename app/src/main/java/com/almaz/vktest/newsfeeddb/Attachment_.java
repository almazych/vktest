package com.almaz.vktest.newsfeeddb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment_ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private Photo__ photo;
    @SerializedName("audio")
    @Expose
    private Audio_ audio;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Photo__ getPhoto() {
        return photo;
    }

    public void setPhoto(Photo__ photo) {
        this.photo = photo;
    }

    public Audio_ getAudio() {
        return audio;
    }

    public void setAudio(Audio_ audio) {
        this.audio = audio;
    }

}