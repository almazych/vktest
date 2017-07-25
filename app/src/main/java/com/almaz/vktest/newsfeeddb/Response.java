package com.almaz.vktest.newsfeeddb;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("profiles")
    @Expose
    private List<Profile> profiles = null;
    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;
    @SerializedName("next_from")
    @Expose
    private String nextFrom;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getNextFrom() {
        return nextFrom;
    }

    public void setNextFrom(String nextFrom) {
        this.nextFrom = nextFrom;
    }

}