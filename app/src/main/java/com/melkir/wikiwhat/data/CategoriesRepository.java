package com.melkir.wikiwhat.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoriesRepository implements CategoriesDataSource {
    private List<Category> mCategories;

    public CategoriesRepository(Context context) {
    }

    @Override
    // TODO Change this method to be asynchronous and return data
    public List<Category> getCategories() {
        mCategories = new ArrayList<>();
//        return Collections.emptyList();
        mCategories.addAll(Arrays.asList(
                new Category(0, "Education", 298),
                new Category(1, "Computer science", 38),
                new Category(2, "French novelists", 40)
        ));

        return mCategories;
    }
}
