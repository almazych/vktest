package com.almaz.vktest.newsfeeddb;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CopyHistory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("from_id")
    @Expose
    private Integer fromId;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("attachments")
    @Expose
    private List<Attachment_> attachments = null;
    @SerializedName("post_source")
    @Expose
    private PostSource_ postSource;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Attachment_> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment_> attachments) {
        this.attachments = attachments;
    }

    public PostSource_ getPostSource() {
        return postSource;
    }

    public void setPostSource(PostSource_ postSource) {
        this.postSource = postSource;
    }

}