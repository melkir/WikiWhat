package com.melkir.wikiwhat.data;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

public class CategoriesRepository implements CategoriesDataSource {
    private List<Category> mCategories;

    public CategoriesRepository(Context context) {
    }

    @Override
    // TODO Change this method to be asynchronous and return data
    public List<Category> getCategories() {
//        return Collections.emptyList();
        return Arrays.asList(new Category(0, "Education aaaaa bbbbb ccccc dddddd eeeee fffff ggggg", 298), new Category(1, "Computer science", 38));
    }
}
