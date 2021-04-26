package com.bhim.waleapod;

import com.google.gson.annotations.SerializedName;

public class ModelAPOD {

    @SerializedName("date")
    private String date;

    @SerializedName("media_type")
    private String mediaType;

    @SerializedName("hdurl")
    private String hdurl;

    @SerializedName("service_version")
    private String serviceVersion;

    @SerializedName("explanation")
    private String explanation;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "date = '" + date + '\'' +
                        ",media_type = '" + mediaType + '\'' +
                        ",hdurl = '" + hdurl + '\'' +
                        ",service_version = '" + serviceVersion + '\'' +
                        ",explanation = '" + explanation + '\'' +
                        ",title = '" + title + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}