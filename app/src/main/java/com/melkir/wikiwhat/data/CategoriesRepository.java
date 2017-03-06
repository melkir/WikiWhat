package com.melkir.wikiwhat.data;

import android.content.Context;

import java.util.Collections;
import java.util.List;

public class CategoriesRepository implements CategoriesDataSource {
    private List<Category> mCategories;

    public CategoriesRepository(Context context) {
    }

    @Override
    // TODO Change this method to be asynchronous and return data
    public List<Category> getCategories() {
        return Collections.emptyList();
    }
}
