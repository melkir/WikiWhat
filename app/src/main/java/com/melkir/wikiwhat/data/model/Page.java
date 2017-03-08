package com.melkir.wikiwhat.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("extract")
    @Expose
    private String extract;

    public String getTitle() {
        return title;
    }

    public String getExtract() {
        return extract;
    }
}
