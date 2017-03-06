package com.melkir.wikiwhat.data;

import android.content.Context;

import java.util.List;

public class CategoriesRepository implements CategoriesDataSource {
    private List<Category> mCategories;

    public CategoriesRepository(Context context) {
    }

    @Override
    // TODO Change this method to be asynchronous
    public List<Category> getCategories() {
        return mCategories;
    }
}
