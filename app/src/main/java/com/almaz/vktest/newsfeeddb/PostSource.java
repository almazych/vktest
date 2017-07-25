package com.almaz.vktest.newsfeeddb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostSource {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("platform")
    @Expose
    private String platform;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}