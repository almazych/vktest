package com.almaz.vktest.newsfeeddb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Audio {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("lyrics_id")
    @Expose
    private Integer lyricsId;
    @SerializedName("genre_id")
    @Expose
    private Integer genreId;
    @SerializedName("is_hq")
    @Expose
    private Boolean isHq;

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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLyricsId() {
        return lyricsId;
    }

    public void setLyricsId(Integer lyricsId) {
        this.lyricsId = lyricsId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Boolean getIsHq() {
        return isHq;
    }

    public void setIsHq(Boolean isHq) {
        this.isHq = isHq;
    }

}