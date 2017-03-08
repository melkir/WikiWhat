package com.melkir.wikiwhat.data.wikipedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("random")
    @Expose
    private List<Random> randoms = null;

    @SerializedName("categorymembers")
    @Expose
    private List<CategoryMember> categoryMembers = null;

    @SerializedName("pages")
    @Expose
    private List<Page> pages = null;

    public List<Random> getRandoms() {
        return randoms;
    }

    public List<CategoryMember> getCategoryMembers() {
        return categoryMembers;
    }

    public List<Page> getPages() {
        return pages;
    }

}