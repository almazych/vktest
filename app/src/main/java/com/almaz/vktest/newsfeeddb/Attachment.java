package com.almaz.vktest.newsfeeddb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("video")
    @Expose
    private Video video;
    @SerializedName("link")
    @Expose
    private Link link;
    @SerializedName("photo")
    @Expose
    private Photo_ photo;
    @SerializedName("audio")
    @Expose
    private Audio audio;
    @SerializedName("doc")
    @Expose
    private Doc doc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Photo_ getPhoto() {
        return photo;
    }

    public void setPhoto(Photo_ photo) {
        this.photo = photo;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

}