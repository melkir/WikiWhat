package com.melkir.wikiwhat.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {
    @SerializedName("pageid")
    @Expose
    private String pageid;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("extract")
    @Expose
    private String extract;

    public String getPageid() {
        return pageid;
    }

    public String getTitle() {
        return title;
    }

    public String getExtract() {
        return extract;
    }
}
