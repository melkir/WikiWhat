package com.melkir.wikiwhat.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    private int count;

    public Category(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Category(Integer id, String title, int count) {
        this.id = id;
        this.title = title;
        this.count = count;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", title='" + title + '\'' + ", count=" + count + '}';
    }
}