package com.melkir.wikiwhat.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryMember {
    @SerializedName("pageid")
    @Expose
    private Integer pageid;

    @SerializedName("title")
    @Expose
    private String title;

    public Integer getPageid() {
        return pageid;
    }

    public String getTitle() {
        return title;
    }

}
